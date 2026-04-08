package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;


import codeurjc.ssdd.grupo1.trainfyre.service.StationService;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class StationController {

    private final StationService stationService;
    private final UserService userService;

    @GetMapping(value = "/stations")
    public String getStations(Model model) {

        log.info("getStations");

        model.addAttribute("title", "Estaciones");
        model.addAttribute("stations", stationService.getAllStations());

        return "stations";
    }
}
