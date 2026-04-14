package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;

import org.springframework.ui.Model;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    @Autowired
    private IncidenceService incidenceService;

    @Autowired
    private LineService lineService;

    @Autowired
    private LineMapper lineMapper;

    @GetMapping({"/", "index"})
    public String index(Model model) {
        log.info("Loading index page");

        model.addAttribute("title", "Index");
        model.addAttribute("interactiveMapScript", "pages/index.js");
        try {
            List<IncidenceDTO> incidenceDTOs = incidenceService.getAllIncidences();
            model.addAttribute("incidences", incidenceDTOs);
        } catch (Exception e) {
            log.error("Error loading incidences: {}", e.getMessage());
            model.addAttribute("incidences", Collections.emptyList());
        }

        return "index";
    }

    @GetMapping("/index/filtered")
    public String indexFiltered(Model model, @RequestParam String lineName) {
        log.info("Loading filtered index page for line: {}", lineName);

        model.addAttribute("title", "Index");
        model.addAttribute("interactiveMapScript", "pages/index.js");

        try {
            Line line = lineService.findLineByName(lineName);
            List<IncidenceDTO> incidenceDTOs = incidenceService.getAllIncidencesAffectingLineAsDTO(line);
            model.addAttribute("incidences", incidenceDTOs);
        } catch (Exception e) {
            log.error("Error loading filtered incidences: {}", e.getMessage());
            model.addAttribute("incidences", Collections.emptyList());
        }

        return "index";
    }
}
