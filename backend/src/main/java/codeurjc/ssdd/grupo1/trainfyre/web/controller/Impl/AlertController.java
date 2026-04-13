package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.AlertRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.LineRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Impl.AlertDtoToAlert;
import codeurjc.ssdd.grupo1.trainfyre.service.AlertService;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
@RequiredArgsConstructor
public class AlertController {

    /*
     * private final Impl.AlertDtoToAlert alertDtoToAlert_1;
     * 
     * private final Impl.AlertDtoToAlert alertDtoToAlert;
     */

    private final AlertRepository alertRepository;

    private final Logger logger = LoggerFactory.getLogger(AlertController.class);

    private final LineRepository lineRepository;
    private final UserRepository userRepository;

    private final AlertService alertService;
    private final UserService userService;

    /*
     * AlertController(Impl.AlertDtoToAlert alertDtoToAlert, Impl.AlertDtoToAlert
     * alertDtoToAlert_1) {
     * this.alertDtoToAlert = alertDtoToAlert;
     * this.alertDtoToAlert_1 = alertDtoToAlert_1;
     * }
     */

    /*
     * AlertController(AlertRepository alertRepository) {
     * this.alertRepository = alertRepository;
     * }
     */

    @GetMapping(value = "/alert/form")
    public String formAlert(Model model) {

        logger.info("getAlerts");

        // I need the repository to get the line.
        model.addAttribute("lines", lineRepository.findAll());

        // I'll use an example for the lines for the time being
        // model.addAttribute("lines", Arrays.asList("C-1", "C-2", "C-3"));

        // Include the slider.js
        model.addAttribute("pageScriptsBottom", List.of("components/slider.js"));

        model.addAttribute("title", "Alert form");

        return "form-alert";
    }

    @PostMapping(value = "/alert/modify")
    public String formModify(Model model, @RequestParam String id) {
        logger.info("getAlerts");

        Long ident = Long.parseLong(id);
        String error = null;

        // Get the alert to modify.
        Optional<Alert> alert = alertRepository.findById(ident);
        Alert currentAlert;
        String[] listStart;
        String[] listEnd;

        int startTime;
        int endTime;

        if (alert.isEmpty()) {// Alert not found.
            error = "Alert not found.";
            model.addAttribute(error, "error");
            return "error";
        }

        // I get the alert and translate the times to input values.
        currentAlert = alert.get();
        listStart = currentAlert.getStartHour().split(":");
        startTime = Integer.parseInt(listStart[0]) * 60 + Integer.parseInt(listStart[1]);

        listEnd = currentAlert.getEndHour().split(":");
        endTime = Integer.parseInt(listEnd[0]) * 60 + Integer.parseInt(listEnd[1]);

        // I need the repository to get the line.
        model.addAttribute("lines", lineRepository.findAll());
        // Include the slider.js
        model.addAttribute("pageScriptsBottom", List.of("components/slider.js"));
        // Include title.
        model.addAttribute("title", "Alert form");
        // Include id.
        model.addAttribute("id", id);
        // Include modify indication.
        model.addAttribute("modify", true);
        // Include alert.
        model.addAttribute("alert", currentAlert);
        // Add the start and end times in slider language.
        model.addAttribute("startTime", Integer.toString(startTime));
        model.addAttribute("endTime", Integer.toString(endTime));

        return "form-alert";
    }

    @PostMapping(value = "/alert/added")
    public String formAdded(Model model, @RequestParam String line, @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam String min, @RequestParam String max,
            @AuthenticationPrincipal UserDetails user) {

        // Registro la alerta.

        String error = null;

        AppUser appUser;
        Line linereal;

        // Get the line from the DB.
        Optional<Line> lineO = lineRepository.findByName(line);
        if (lineO.isEmpty()) {
            error = "Línea no encontrada.";
        } else {// Associate the alert to the user.
            linereal = lineO.get();
            UserInfoDTO useriDto = userService.findUser(user);
            Optional<AppUser> userO = userRepository.findByUsername(useriDto.username());

            if (userO.isPresent()) {
                // Obtain the user and add the alert.
                appUser = userO.get();
                AlertRegistrationDTO alertDto = new AlertRegistrationDTO(linereal, startDate, endDate, min, max,
                        appUser);
                alertService.registerAlert(alertDto, appUser);
            } else {
                error = "Usuario no encontrado.";
            }
        }
        model.addAttribute("title", "Alert added");

        return "alert_added";
    }

    @GetMapping(value = "/alert/table")
    public String alertTable(Model model, @AuthenticationPrincipal UserDetails user, Pageable page) {
        AppUser appUser;
        Boolean thereIs = false;
        UserInfoDTO useriDto = userService.findUser(user);
        Optional<AppUser> userO = userRepository.findByUsername(useriDto.username());
        List<Alert> alerts;

        Boolean hasPrev = false;
        Boolean hasNext = false;
        int prev = page.getPageNumber() - 1;
        int next = page.getPageNumber() + 1;

        model.addAttribute("title", "Alert table");

        if (userO.isPresent()) {
            // Obtain the user get the alerts.
            appUser = userO.get();
            alerts = alertRepository.findByUserOrderByLine(appUser, page);
            if (!alerts.isEmpty()) {
                thereIs = true;
            }

            model.addAttribute("alerts", alerts);
        } else {// User not found.
            return "error";
        }

        hasPrev = page.getPageNumber() >= 1;
        hasNext = (page.getPageNumber() + 1) * page.getPageSize() < alertRepository.findByUser(appUser).size();
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        model.addAttribute("thereIs", thereIs);
        return "user_alerts";
    }

    @PostMapping(value = "/alert/modified")
    public String formModified(Model model, @RequestParam String line, @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam String min, @RequestParam String max, @RequestParam String id) {
        Long currentAlertId = Long.parseLong(id);
        Optional<Alert> alertO = alertRepository.findById(currentAlertId);
        String error = null;
        Alert currentAlert;

        Line linereal;

        // Get the line from the DB.
        Optional<Line> lineO = lineRepository.findByName(line);
        if (lineO.isEmpty()) {
            error = "Línea no encontrada.";
            model.addAttribute("error", error);
            return "error";
        }

        if (alertO.isEmpty()) {// No se encuentra la alerta.
            error = "No se encuentra la alerta.";
            model.addAttribute("error", error);
            return "error";
        }

        // Obtain the alert to modify it.
        currentAlert = alertO.get();

        // Update the alert.
        linereal = lineO.get();
        currentAlert.setLine(linereal);
        currentAlert.setStartDate(startDate);
        currentAlert.setEndDate(endDate);
        currentAlert.setStartHour(min);
        currentAlert.setEndHour(max);

        // Save the alert.
        alertRepository.save(currentAlert);

        model.addAttribute("title", "Alert modified");

        return "alert_added";
    }

    @PostMapping(value = "alert/delete")
    public String deleteAlert(Model model, @RequestParam String id, @AuthenticationPrincipal UserDetails user,
            Pageable page) {
        String error = null;
        Optional<Alert> alertO;
        Long currentAlertId = Long.parseLong(id);

        AppUser appUser;
        Boolean thereIs = false;
        UserInfoDTO useriDto = userService.findUser(user);
        Optional<AppUser> userO = userRepository.findByUsername(useriDto.username());
        List<Alert> alerts;
        Alert currentAlert;
        Line currentLine;

        Boolean hasPrev = false;
        Boolean hasNext = false;
        int prev = page.getPageNumber() - 1;
        int next = page.getPageNumber() + 1;

        // Get the alert and delete it.
        alertO = alertRepository.findById(currentAlertId);

        if (alertO.isEmpty()) {//Alert not found.
            error = "Alerta no encontrada.";
            model.addAttribute("error", error);
            return "error";
        }

        //Delete alert.
        currentAlert = alertO.get();
        currentAlert.setLine(null);
        currentAlert.setUser(null);
        alertRepository.save(currentAlert);
        alertRepository.delete(currentAlert);

        if (userO.isPresent()) {
            // Obtain the user get the alerts.
            appUser = userO.get();
            alerts = alertRepository.findByUserOrderByLine(appUser, page);
            if (!alerts.isEmpty()) {
                thereIs = true;
            }

            model.addAttribute("alerts", alerts);
        } else {// User not found.
            error = "Usuario no encontrado.";
            model.addAttribute("error", error);
            return "error";
        }

        

        hasPrev = page.getPageNumber() >= 1;
        hasNext = (page.getPageNumber() + 1) * page.getPageSize() < alertRepository.findByUser(appUser).size();

        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        model.addAttribute("thereIs", thereIs);
        model.addAttribute("title", "Alert deletion");
        return "user_alerts";
    }

}