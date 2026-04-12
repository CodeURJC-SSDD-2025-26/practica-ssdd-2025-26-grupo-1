package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<Map<String, Object>> lines = lineService.getAllLines().stream()
            .map(line -> Map.<String, Object>of(
                "line", line
            ))
                .toList();
        model.addAttribute("lines", lines);

        return "admin_panel_lines";
    }

    @PostMapping(value = "/admin/admin_panel_lines/delete")
    public String deleteUserFromAdminPanel(
        @RequestParam String linename, 
        Model model
    ) {
        model.addAttribute("title", "Admin Lines Panel");

        List<Map<String, Object>> lines = lineService.getAllLines().stream()
            .map(line -> Map.<String, Object>of(
                "line", line
            ))
                .toList();
        model.addAttribute("lines", lines);

        lineService.deleteLine(linename);

        return "redirect:/admin/admin_panel_lines";
    }

    @PostMapping(value = "admin/admin_panel_lines/update")
    public String updateUser(
        @RequestParam String oldName, 
        @RequestParam String newName, 
        @RequestParam String description,
        Model model
    ) {

        log.info("Admin Updating user {}, to {}", oldName, newName);

        model.addAttribute("title", "Admin Lines Panel");

        List<Map<String, Object>> lines = lineService.getAllLines().stream()
            .map(line -> Map.<String, Object>of(
                "line", line
            ))
                .toList();
        model.addAttribute("lines", lines);

        lineService.updateLine(oldName, newName, description);

        return "redirect:/admin/admin_panel_lines";
    }
}
