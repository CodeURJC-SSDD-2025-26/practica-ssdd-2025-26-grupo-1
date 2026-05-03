package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.*;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.LineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import codeurjc.ssdd.grupo1.trainfyre.appservice.service.IncidenceService;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.LineService;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;

@RestController
@RequestMapping("/api/incidences")
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceRestController {

    private final IncidenceService incidenceService;
    private final LineService lineService;
    private final LineMapper lineMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<IncidenceDTO>> getAllIncidences(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        log.info("GET /api/incidences");
        Page<IncidenceDTO> page = incidenceService.getPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{incidenceId}")
    public ResponseEntity<IncidenceDTO> getIncidenceById(@PathVariable String incidenceId) {
        log.info("GET /api/incidences/{}", incidenceId);
        IncidenceDTO incidence = incidenceService.getIncidenceWithID(incidenceId);
        return ResponseEntity.ok(incidence);
    }

    @PostMapping
    public ResponseEntity<IncidenceDTO> createIncidence(
            @RequestParam String incidenceID,
            @RequestParam INCIDENCE_LEVEL incidenceLevel,
            @RequestParam INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam INCIDENCE_STATUS status,
            @RequestParam List<String> affectedLineNames) {

        log.info("POST /api/incidences. Incidence {}", incidenceID);

        List<Line> affectedLines = affectedLineNames != null
                ? affectedLineNames.stream()
                        .map(lineService::getLineByName)
                        .map(lineMapper::toLine)
                        .toList()
                : List.of();

        IncidenceRegistrationDTO dto = new IncidenceRegistrationDTO(
                incidenceID,
                incidenceLevel,
                incidenceType,
                description,
                date,
                status,
                null,
                affectedLines);

        IncidenceDTO created = incidenceService.createIncidence(dto);
        notificateIncidence(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{incidenceId}")
    public ResponseEntity<IncidenceDTO> updateIncidence(
            @PathVariable String incidenceId,
            @RequestParam(required = false) MultipartFile updatedImage,
            @RequestParam(required = false) INCIDENCE_LEVEL incidenceLevel,
            @RequestParam(required = false) INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) INCIDENCE_STATUS status) throws IOException {

        log.info("PUT /api/incidences/{}", incidenceId);

        byte[] imageData;
        if (updatedImage != null) {
            imageData = updatedImage.getBytes();
        } else {
            imageData = null;
        }

        IncidenceRegistrationDTO dto = new IncidenceRegistrationDTO(
                incidenceId,
                incidenceLevel,
                incidenceType,
                description,
                null,
                status,
                imageData,
                null);

        incidenceService.updateIncidence(updatedImage, dto);

        IncidenceDTO updated = incidenceService.getIncidenceWithID(incidenceId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{incidenceId}")
    public ResponseEntity<Void> deleteIncidence(@PathVariable String incidenceId) {
        log.info("DELETE /api/incidences/{}", incidenceId);
        incidenceService.deleteIncidence(incidenceId);
        return ResponseEntity.noContent().build();
    }

    // Método para notificar a los usuarios afectados por una incidencia de forma asincrona no bloqueante
    private void notificateIncidence(IncidenceDTO incidence) {
        if (incidence.status() != INCIDENCE_STATUS.ACTIVE) {
            log.info("Incidence with id {} is not active, skipping notification", incidence.id());
            return;
        }
        SecurityContext context = SecurityContextHolder.getContext();
        CompletableFuture.runAsync(() -> {
            SecurityContextHolder.setContext(context); // propagar contexto
            userService.notifyIncidenceToAffectedUsers(incidence);
        });
    }
}