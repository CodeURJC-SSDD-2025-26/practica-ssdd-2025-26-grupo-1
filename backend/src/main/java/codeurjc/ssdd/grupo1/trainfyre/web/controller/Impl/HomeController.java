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
public class HomeController {
    @GetMapping({"/", "index"})
    public String index(Model model) {
        log.info("Loading index page");

        model.addAttribute("title", "Index");
        model.addAttribute("interactiveMapScript", "components/interactive-map.js");

        return "index";
    }
}
