package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class IncidenceController {
    
    @GetMapping(value = "/incidences")
    public String getIncidences(Model model) {

        log.info("incidences");

        model.addAttribute("title", "Incidencias");

        return "incidences";
    }
}
