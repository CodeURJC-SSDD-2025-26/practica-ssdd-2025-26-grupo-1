package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;

import org.springframework.ui.Model;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    @Autowired
    private IncidenceService incidenceService;

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
}
