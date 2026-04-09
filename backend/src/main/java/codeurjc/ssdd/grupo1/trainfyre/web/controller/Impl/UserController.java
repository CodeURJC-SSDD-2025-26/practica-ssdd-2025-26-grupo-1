package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        log.info("login");

        if (error != null) {
            model.addAttribute("error", "Se ha producido un error al intentar login");
        }

        model.addAttribute("title", "Login");

        return "login";
    }

    /*
    No soy capaz de tener una pagina para el logout, mas tarde la implementaré
    @GetMapping(value = "/logout")
    public String logout(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("logout");

        if (error != null) {
            model.addAttribute("error", "Se ha producido un error al intentar logout");
        }

        model.addAttribute("title", "Logout");

        return "successful_logout";
    }*/

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {

        log.info("Loading registration form");

        model.addAttribute("title", "Register");

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("User") UserRegistrationtDTO userRegistrationtDTO, Model model){

        log.info("Registration petition {}", userRegistrationtDTO.toString());

        userService.createUser(userRegistrationtDTO);
        return "index";
    }

    @GetMapping(value = "/registered/settings")
    public String showSettings(Model model) {

        log.info("Loading settings");

        model.addAttribute("title", "Settings");
        model.addAttribute("pageScriptsBottom", List.of("components/change-settings.js"));

        return "settings";
    }

    @PostMapping(value = "/registered/settings")
    public String updateSettings(@ModelAttribute("User") UserRegistrationtDTO newUserData, @AuthenticationPrincipal UserDetails currentUser, Model model){

        log.info("Updating settings");

        model.addAttribute("title", "Settings");

        userService.updateUser(currentUser, newUserData);

        return "settings";
    }

    @GetMapping(value = "/admin/admin_panel")
    public String showAdminPanel(Model model) {
        log.info("Loading admin panel");

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("users", userService.findAllUsers());

        return "admin_panel";
    }
}
