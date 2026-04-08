package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;


import codeurjc.ssdd.grupo1.trainfyre.service.Impl.AuthenticatorUserService;
import codeurjc.ssdd.grupo1.trainfyre.service.StationService;
import codeurjc.ssdd.grupo1.trainfyre.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@RequiredArgsConstructor
public class AlertController {

    private final Logger logger = LoggerFactory.getLogger(AlertController.class);
    private final AlertService alertService;

    @GetMapping(value = "/alert/form")
    public String formAlert(Model model) {

        logger.info("getAlerts");

        //I need the repository to get the line.
        //model.addAttribute("line", );

        //I'll use an example for the lines for the time being
        model.addAttribute("lines", Arrays.asList("C-1", "C-2", "C-3"));


        model.addAttribute("title", "Alert form");


        return "form-alert";
    }

    /*@GetMapping(value = "/alert/added")
    public String formAdded(Model model, @RequestParam List<Line> lines @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam LocalTime min, @RequestParam LocalTime max) {

        //Poner servicio o método del repositorio de añadir alerta.
        

        return "alert_added";
    }*/
}