package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;


import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
