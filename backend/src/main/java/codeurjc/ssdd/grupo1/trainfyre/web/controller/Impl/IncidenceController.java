package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.*;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.IncidenceRepository;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceController {

    private final IncidenceService incidenceService;
    private final LineService lineService;
    private final LineMapper lineMapper;

    private final UserService userService;

    @GetMapping(value = "/incidences")
    public String getIncidences(Model model) {
        log.info("Loading incidences page");

        model.addAttribute("title", "Incidencias");
        model.addAttribute("piechartScript", "components/pie-chart.js");
        model.addAttribute("heatmapScript", "components/heatmap.js");

        String piechartJson = incidenceService.generatePieChartJSON();
        model.addAttribute("piechart_json", piechartJson);

        String heatmapJson = incidenceService.generateHeatmapJSON();
        model.addAttribute("heatmap_json", heatmapJson);

        return "incidences";
    }

    @GetMapping(value = "/admin/admin_panel_incidences")
    public String showAdminPanel(Model model, @PageableDefault(page = 0, size = 5) Pageable page) {
        log.info("Loading admin panel");

        Boolean hasPrev = false;
        Boolean hasNext = false;
        int prev = page.getPageNumber() - 1;
        int next = page.getPageNumber() + 1;
        Page<Incidence> incidents = incidenceService.findAll(page);

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("lines", lineService.getAllLines());

        List<Map<String, Object>> incidences = incidenceService.getAllIncidencesWithID().stream()
                .map(incidence -> Map.<String, Object>of(
                        "incidence", incidence))
                .toList();

        hasPrev = page.getPageNumber() >= 1;
        hasNext = (page.getPageNumber() + 1) * page.getPageSize() < incidences.size();

        model.addAttribute("incidences", incidents);
        model.addAttribute("hasPrevious", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("previous", prev);
        model.addAttribute("next", next);

        return "admin_panel_incidences";
    }

    @PostMapping(value = "/admin/admin_panel_incidences/add")
    public String createIncidenceFromAdminPanel(
            @RequestParam(required = false) MultipartFile updatedImage,
            @RequestParam(required = false) INCIDENCE_LEVEL incidenceLevel,
            @RequestParam(required = false) INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(required = false) INCIDENCE_STATUS status,
            @RequestParam(required = false) List<String> affectedLineNames,
            Model model) {

        

        log.info("Admin creating new incidence");
        model.addAttribute("title", "Admin Panel");
        model.addAttribute("lines", lineService.getAllLines());

        List<Line> affectedLines = affectedLineNames != null
                ? affectedLineNames.stream()
                        .map(lineService::getLineByName)
                        .map(lineMapper::toLine)
                        .toList()
                : null;

        byte[] imageData = null;
        if (updatedImage != null) {
            try {
                imageData = updatedImage.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e + "error en la lectura del archivo");
            }
        }

        IncidenceRegistrationDTO dto = new IncidenceRegistrationDTO(
                incidenceLevel,
                incidenceType,
                description,
                date,
                status,
                imageData,
                affectedLines);

        try {
            IncidenceDTO newIncidence = incidenceService.createIncidence(dto);
            notificateIncidence(newIncidence);
            log.info("Incidence created successfully");
        } catch (ResponseStatusException e) {
            log.error("Error creating incidence: {}", e.getReason());
            model.addAttribute("error", "Error al crear la incidencia");
            return "admin_panel_incidences";
        }

        return "redirect:/admin/admin_panel_incidences";
    }

    @PostMapping(value = "/admin/admin_panel_incidences/delete")
    public String deleteIncidenceFromAdminPanel(@RequestParam Long id, Model model) {
        log.info("Admin deleting incidence with id: {}", id);

        model.addAttribute("title", "Admin Panel");

        try {
            incidenceService.deleteIncidence(id);
            log.info("Incidence with id {} deleted successfully", id);
        } catch (ResponseStatusException e) {
            log.error("Error deleting incidence with id {}: {}", id, e.getReason());
            model.addAttribute("error", "No se encontró la incidencia con ID: " + id);
            return "admin_panel_incidences";
        }

        return "redirect:/admin/admin_panel_incidences";
    }

    @PostMapping(value = "admin/admin_panel_incidences/update")
    public String updateIncidenceFromAdminPanel(@RequestParam Long id,
            @RequestParam(required = false) MultipartFile updatedImage,
            @RequestParam(required = false) INCIDENCE_LEVEL incidenceLevel,
            @RequestParam(required = false) INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) INCIDENCE_STATUS status,
            Model model) {

        log.info("Admin updating incidence with id: {}", id);
        model.addAttribute("title", "Admin Panel");
        model.addAttribute("lines", lineService.getAllLines());

        byte[] imageData = null;
        if (updatedImage != null) {
            try {
                imageData = updatedImage.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e + "error en la lectura del archivo");
            }
        }

        IncidenceRegistrationDTO incidenceRegistrationDTO = new IncidenceRegistrationDTO(
                incidenceLevel,
                incidenceType,
                description,
                null,
                status,
                imageData,
                null);

        try {
            incidenceService.updateIncidence(id, updatedImage, incidenceRegistrationDTO);
            log.info("Incidence with id {} updated successfully", id);
        } catch (ResponseStatusException e) {
            log.error("Error updating incidence with id {}: {}", id, e.getReason());
            model.addAttribute("error", "No se encontró la incidencia con ID: " + id);
            return "admin_panel_incidences";
        }

        return "redirect:/admin/admin_panel_incidences";
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
