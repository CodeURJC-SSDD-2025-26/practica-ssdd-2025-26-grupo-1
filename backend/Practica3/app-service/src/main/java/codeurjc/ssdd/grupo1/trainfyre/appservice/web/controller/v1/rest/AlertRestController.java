package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertShowDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.AlertMapper;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.AlertService;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/")
public class AlertRestController {

    @Autowired
    AlertMapper alertMapper;

    @Autowired
    AlertService alertService;

    @Autowired
    UserService userService;

    @GetMapping("/alerts/{page}")
    public Page<AlertShowDTO> getAlerts(@AuthenticationPrincipal UserDetails user, @PathVariable Pageable page) {
        UserDTO userDto = userService.giveUser(user);
        Page<AlertDTO> list = alertService.getPage(userDto, page);
        return list.map(thing -> alertMapper.alertDTOtoShowDto(thing));
    }


    @PostMapping("/alerts/")
    public ResponseEntity<AlertRegistrationDTO> createAlert(@AuthenticationPrincipal UserDetails user, @RequestBody AlertRegistrationDTO alert) {
        UserDTO userDto = userService.giveUser(user);
        

        if (!alertService.isValidTimeRange(alert.startHour(), alert.endHour()) || !alertService.isValidDate(alert.startDate(), alert.endDate())) {//Impossible time range.
            throw  new IllegalArgumentException("No se puede poner un instante inicial posterior al final.");
        } else {
            AlertShowDTO alertDto = alertMapper.alertDTOtoShowDto(alertService.registerAlert(alert, userDto));
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(alertDto.id()).toUri();
            return ResponseEntity.created(location).body(alert);
        }
    }

    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<AlertShowDTO> deleteAlert(@PathVariable long id) {
        return alertService.deleteAlertRest(id);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<AlertShowDTO> replaceAlert(@AuthenticationPrincipal UserDetails user, @PathVariable long id, 
    @RequestBody AlertRegistrationDTO updatedAlert) {
        UserDTO userDto = userService.giveUser(user);
        if (!alertService.isValidTimeRange(updatedAlert.startHour(), updatedAlert.endHour()) || !alertService.isValidDate(updatedAlert.startDate(), updatedAlert.endDate())) {//Impossible time range.
            throw  new IllegalArgumentException("No se puede poner un instante inicial posterior al final.");
        } else {
            return alertService.updateAlert(userDto, id, updatedAlert);
        }     
    }
}
