package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.service.Impl.AuthenticatorUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
@RequiredArgsConstructor
public class AuthenticatorController {

    private final Logger logger = LoggerFactory.getLogger(StationController.class);
    private final AuthenticatorUserService authenticatorUserService;

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        logger.info("login");

        if (error != null) {
            model.addAttribute("error", "Se ha producido un error al intentar login");
        }

        model.addAttribute("title", "Login");

        return "login";
    }
}
