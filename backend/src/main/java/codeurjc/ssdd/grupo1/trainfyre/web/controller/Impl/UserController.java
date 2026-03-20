package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        logger.info("login");

        if (error != null) {
            model.addAttribute("error", "Se ha producido un error al intentar login");
        }

        model.addAttribute("title", "Login");

        return "login";
    }

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {

        logger.info("Loading registration form");

        model.addAttribute("title", "Register");

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("User") UserRegistrationtDTO userRegistrationtDTO, Model model){

        logger.info("Registration petition");

        logger.info(userRegistrationtDTO.toString());

        logger.info(userService.registerUser(userRegistrationtDTO).toString());

        return "index";
    }
}
