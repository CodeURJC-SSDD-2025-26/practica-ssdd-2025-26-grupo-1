package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.AlertRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.LineRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.AlertMapper;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.AlertService;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.domain.Page;
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

    // private final Impl.AlertDtoToAlert alertDtoToAlert;

    // private final Impl.AlertDtoToAlert alertDtoToAlert;

    private final Logger logger = LoggerFactory.getLogger(AlertController.class);

    private final LineRepository lineRepository;
    private final UserRepository userRepository;

    private final AlertService alertService;

    private final UserService userService;
    private final LineService lineService;

    private final UserMapper userMapper;
    private final LineMapper lineMapper;
    private final AlertMapper alertMapper;

    /*
     * AlertController(Impl.AlertDtoToAlert alertDtoToAlert) {
     * this.alertDtoToAlert = alertDtoToAlert;
     * }
     */

    /*
     * AlertController(Impl.AlertDtoToAlert alertDtoToAlert) {
     * this.alertDtoToAlert = alertDtoToAlert;
     * }
     */

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
        Alert alert = alertMapper.alertDTOToAlert(alertService.getAlert(ident));
        AlertDTO currentAlert;
        String[] listStart;
        String[] listEnd;

        int startTime;
        int endTime;

        // I get the alert and translate the times to input values.
        currentAlert = alertMapper.alertToDTO(alert);
        listStart = alert.getStartHour().split(":");
        startTime = Integer.parseInt(listStart[0]) * 60 + Integer.parseInt(listStart[1]);

        listEnd = alert.getEndHour().split(":");
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
        model.addAttribute("currentAlert", currentAlert);
        // Add the start and end times in slider language.
        model.addAttribute("startTime", Integer.toString(startTime));
        model.addAttribute("endTime", Integer.toString(endTime));

        model.addAttribute("startInput", currentAlert.startHour());
        model.addAttribute("endInput", currentAlert.endHour());

        model.addAttribute("alert", currentAlert);

        return "form-alert";
    }

    @PostMapping(value = "/alert/added")
    public String formAdded(Model model, @RequestParam String line, @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam String min, @RequestParam String max,
            @AuthenticationPrincipal UserDetails user) {

        LocalDate start;
        LocalDate end;
        String error = null;

        UserDTO userDto = userService.giveUser(user);
        Line linereal;

        // First I check if the date range makes sense.
        String[] startDay = startDate.split("-");
        String[] endDay = endDate.split("-");

        start = LocalDate.of(Integer.parseInt(startDay[0]), Integer.parseInt(startDay[1]),
                Integer.parseInt(startDay[2]));
        end = LocalDate.of(Integer.parseInt(endDay[0]), Integer.parseInt(endDay[1]), Integer.parseInt(endDay[2]));

        if (end.compareTo(start) < 0) {// Impossible range.
            // I need the repository to get the line.
            model.addAttribute("lines", lineRepository.findAll());

            // Include the slider.js
            model.addAttribute("pageScriptsBottom", List.of("components/slider.js"));

            model.addAttribute("title", "Alert form");

            model.addAttribute("problem", "La fecha inicial no puede ser posterior a la final");

            return "form-alert";
        }

        // Get the line from the DB.
        LineDTO lineDto = lineService.getLineByName(line);

        // Associate the alert to the user.

        AlertRegistrationDTO alertDto = new AlertRegistrationDTO(lineMapper.toLine(lineDto), startDate, endDate, min,
                max,
                userMapper.userDTOToAppUser(userDto));
        alertService.registerAlert(alertDto, userDto);

        model.addAttribute("title", "Alert added");

        return "alert_added";
    }

    @GetMapping(value = "/alert/table")
    public String alertTable(Model model, @AuthenticationPrincipal UserDetails user, Pageable page) {
        Boolean thereIs = false;
        UserInfoDTO useriDto = userService.findUser(user);
        UserDTO userO = userService.giveUser(user);
        Page<AlertDTO> alerts;

        Boolean hasPrev = false;
        Boolean hasNext = false;
        int prev = page.getPageNumber() - 1;
        int next = page.getPageNumber() + 1;

        alerts = alertService.getPage(userO, page);

        model.addAttribute("title", "Alert table");

        hasPrev = page.getPageNumber() >= 1;
        hasNext = page.getPageNumber() < alerts.getTotalPages() - 1;
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
        model.addAttribute("alerts", alerts);

        model.addAttribute("thereIs", thereIs);
        return "user_alerts";
    }

    @PostMapping(value = "/alert/modified")
    public String formModified(Model model, @RequestParam String line, @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam String min, @RequestParam String max, @RequestParam String id) {
        Long currentAlertId = Long.parseLong(id);
        AlertDTO alertDto = alertService.getAlert(currentAlertId);
        String error = null;
        AlertDTO currentAlert = alertService.getAlert(currentAlertId);

        Line linereal;
        LocalDate start;
        LocalDate end;

        int startTime;
        int endTime;

        String[] listStart;
        String[] listEnd;

        // First I check if the date range makes sense.

        // Get the line from the DB.
        LineDTO lineDto = lineService.getLineByName(line);

        alertDto = new AlertDTO(Long.parseLong(id), lineMapper.toLine(lineDto), startDate, endDate, min, max,
                new AppUser());

        // Obtain the id to modify the alert.

        String[] startDay = startDate.split("-");
        String[] endDay = endDate.split("-");

        start = LocalDate.of(Integer.parseInt(startDay[0]), Integer.parseInt(startDay[1]),
                Integer.parseInt(startDay[2]));
        end = LocalDate.of(Integer.parseInt(endDay[0]), Integer.parseInt(endDay[1]), Integer.parseInt(endDay[2]));

        listStart = currentAlert.startHour().split(":");
        startTime = Integer.parseInt(listStart[0]) * 60 + Integer.parseInt(listStart[1]);

        listEnd = currentAlert.endHour().split(":");
        endTime = Integer.parseInt(listEnd[0]) * 60 + Integer.parseInt(listEnd[1]);

        if (end.compareTo(start) < 0) {// Impossible range.
            // I need the repository to get the line.
            model.addAttribute("lines", lineRepository.findAll());

            // Include the slider.js
            model.addAttribute("pageScriptsBottom", List.of("components/slider.js"));

            model.addAttribute("title", "Alert form");

            model.addAttribute("problem", "La fecha inicial no puede ser posterior a la final");

            // It's necessary to remember what we are modifying.
            // Include id.
            model.addAttribute("id", id);
            // Include modify indication.
            model.addAttribute("modify", true);
            // Include alert.
            model.addAttribute("alert", currentAlert);
            // Add the start and end times in slider language.
            model.addAttribute("startTime", start);
            model.addAttribute("endTime", end);

            model.addAttribute("startDate", currentAlert.startDate());
            model.addAttribute("endDate", currentAlert.endDate());

            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

            model.addAttribute("startInput", currentAlert.startHour());
            model.addAttribute("endInput", currentAlert.endHour());

            return "form-alert";
        }

        // Update the alert.
        alertService.updateAlert(alertDto);

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
        UserDTO userDto = userService.giveUser(user);
        Page<AlertDTO> alerts;
        Alert currentAlert;

        Boolean hasPrev = false;
        Boolean hasNext = false;
        int prev = page.getPageNumber() - 1;
        int next = page.getPageNumber() + 1;

        // Get the alert and delete it.
        alertService.deleteAlert(Long.parseLong(id));

        alerts = alertService.getPage(userDto, page);

        hasPrev = page.getPageNumber() >= 1;
        hasNext = page.getPageNumber() < alerts.getTotalPages() - 1;

        model.addAttribute("alerts", alerts);

        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        model.addAttribute("thereIs", thereIs);
        model.addAttribute("title", "Alert deletion");
        return "user_alerts";
    }

}