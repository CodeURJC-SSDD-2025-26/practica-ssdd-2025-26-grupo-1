package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
