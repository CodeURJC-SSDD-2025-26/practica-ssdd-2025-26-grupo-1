package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;

import java.util.List;

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
        model.addAttribute("heatmapScript", List.of("components/heatmap.js"));

        String heatmapJson = incidenceService.generateHeatmapJSON();
        model.addAttribute("heatmap_json", heatmapJson);

        return "incidences";
    }
}
