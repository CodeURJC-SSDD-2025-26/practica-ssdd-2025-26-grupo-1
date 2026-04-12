package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;


import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class LineController{

    private final LineService lineService;

    @GetMapping(value = "/lines")
    public String getLines(Model model) {

        log.info("getLines");

        model.addAttribute("title", "Lineas");
        model.addAttribute("lines", lineService.getAllLines());

        return "lines";
    }

    @GetMapping(value = "/admin/admin_panel_lines")
    public String showAdminPanel(Model model) {
        log.info("Loading admin lines panel");

        model.addAttribute("title", "Admin Lines Panel");
        //model.addAttribute("roles", Role.values());

        //List<LineDTO> lines = lineService.getAllLines();
        //model.addAttribute("lines", lines);

        return "admin_panel";
    }
}
