package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.IncidenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/incidences")
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceRestController {

    private final IncidenceService incidenceService;

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
}