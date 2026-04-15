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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import codeurjc.ssdd.grupo1.trainfyre.mapper.IncidenceMapper;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceController {

    private final IncidenceService incidenceService;
    private final LineService lineService;
    private final LineMapper lineMapper;
    private final IncidenceMapper incidenceMapper;
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
        Page<Incidence> incidences = incidenceService.findAll(page);
        List<Incidence> incidencesPageList = incidences.getContent();

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("lines", lineService.getAllLines());

        List<Map<String, Object>> incidencesToShow = incidencesPageList.stream()
                .map(incidence -> Map.of(
                        "incidence", incidence,
                        "image", incidence.getImage() != null
                                ? "data:image/png;base64," +Base64.getEncoder().encodeToString(incidence.getImage())
                                : ""
                ))
                .toList();

        hasPrev = page.getPageNumber() >= 1;
        hasNext = (page.getPageNumber() + 1) * page.getPageSize() < incidenceService.getAllIncidences().size();

        model.addAttribute("incidences", incidencesToShow);
        model.addAttribute("hasPrevious", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("previous", prev);
        model.addAttribute("next", next);

        return "admin_panel_incidences";
    }

    @GetMapping("/registered/incidence/{incidenceID}")
    public String showIncidenceDetails(@PathVariable String incidenceID, Model model) {
        Incidence incidence = incidenceMapper.toIncidence(incidenceService.getIncidenceWithID(incidenceID));

        model.addAttribute("title", "Incidence" + incidenceID);
        model.addAttribute("incidence", incidence);
        model.addAttribute("incidenceImage", incidence.getImage() != null
                    ? "data:image/png;base64," + Base64.getEncoder().encodeToString(incidence.getImage())
                    : "");
        model.addAttribute("incidenceDate", incidence.getDate().toString().replace("T", " "));

        return "incidence_page";
    }

    @PostMapping(value = "/admin/admin_panel_incidences/add")
    public String createIncidenceFromAdminPanel(
            @RequestParam(required = true) String incidenceID,
            @RequestParam(required = true) INCIDENCE_LEVEL incidenceLevel,
            @RequestParam(required = true) INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(required = true) INCIDENCE_STATUS status,
            @RequestParam(required = true) List<String> affectedLineNames,
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

        IncidenceRegistrationDTO dto = new IncidenceRegistrationDTO(
                incidenceID,
                incidenceLevel,
                incidenceType,
                description,
                date,
                status,
                null,
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
    public String deleteIncidenceFromAdminPanel(@RequestParam String incidenceId, Model model) {
        log.info("Admin deleting incidence with id: {}", incidenceId);

        model.addAttribute("title", "Admin Panel");

        try {
            incidenceService.deleteIncidence(incidenceId);
            log.info("Incidence with id {} deleted successfully", incidenceId);
        } catch (ResponseStatusException e) {
            log.error("Error deleting incidence with id {}: {}", incidenceId, e.getReason());
            model.addAttribute("error", "No se encontró la incidencia con ID: " + incidenceId);
            return "admin_panel_incidences";
        }

        return "redirect:/admin/admin_panel_incidences";
    }

    @PostMapping(value = "admin/admin_panel_incidences/update")
    public String updateIncidenceFromAdminPanel(@RequestParam(required = true) String incidenceId,
            @RequestParam(required = false) MultipartFile updatedImage,
            @RequestParam(required = false) INCIDENCE_LEVEL incidenceLevel,
            @RequestParam(required = false) INCIDENCE_TYPE incidenceType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) INCIDENCE_STATUS status,
            Model model) {

        log.info("Admin updating incidence with id: {}", incidenceId);
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
                incidenceId,
                incidenceLevel,
                incidenceType,
                description,
                null,
                status,
                imageData,
                null);

        try {
            incidenceService.updateIncidence(updatedImage, incidenceRegistrationDTO);
            log.info("Incidence with id {} updated successfully", incidenceId);
        } catch (ResponseStatusException e) {
            log.error("Error updating incidence with id {}: {}", incidenceId, e.getReason());
            model.addAttribute("error", "No se encontró la incidencia con ID: " + incidenceId);
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
