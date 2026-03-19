package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;


import codeurjc.ssdd.grupo1.trainfyre.service.StationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@RequiredArgsConstructor
public class StationController {

    private final Logger logger = LoggerFactory.getLogger(StationController.class);
    private final StationService stationService;

    @GetMapping(value = "/stations")
    public String getStations(Model model) {

        logger.info("getStations");

        model.addAttribute("title", "Estaciones");
        model.addAttribute("name", "Pablo");
        model.addAttribute("stations", stationService.getAllStations());

        return "stations";
    }
}
