package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;

import java.util.List;
import java.util.Map;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceController {

    private final IncidenceService incidenceService;
    
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
    public String showAdminPanel(Model model) {
        log.info("Loading admin panel");

        model.addAttribute("title", "Admin Panel");

        List<Map<String, Object>> incidences = incidenceService.getAllIncidences().stream()
            .map(incidence -> Map.<String, Object>of(
                "incidence", incidence
            ))
                .toList();
        model.addAttribute("incidences", incidences);

        return "admin_panel_incidences";
    }

    @PostMapping(value = "/admin/admin_panel_incidences/delete")
    public String deleteIncidenceFromAdminPanel(@RequestParam Long id, Model model) {
        model.addAttribute("title", "Admin Panel");

        

        return "/admin_panel_incidences";
    }

    @PostMapping(value = "admin/admin_panel_incidences/update")
    public String updateUser(Model model) {

        log.info("Admin updating incidence");

        model.addAttribute("title", "Admin Panel");

        return "admin_panel_incidences";
    }
}
