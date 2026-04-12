package codeurjc.ssdd.grupo1.trainfyre.web.controller.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
            model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtalo de nuevo.");
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

    @GetMapping(value = "/registered/settings")
    public String showSettings(Model model) {

        log.info("Loading settings");

        model.addAttribute("title", "Settings");
        model.addAttribute("pageScriptsBottom", List.of("components/change-settings.js"));

        return "settings";
    }

    @PostMapping(value = "/registered/settings")
    public String updateSettings(@ModelAttribute("User") UserRegistrationtDTO newUserData, @RequestParam(value = "image", required = false) MultipartFile image, @AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request, Model model){

        log.info("Updating account {} to {}", currentUser, newUserData);

        model.addAttribute("title", "Settings");
        model.addAttribute("pageScriptsBottom", List.of("components/change-settings.js"));

        UserDetails updatedUser = userService.updateUser(currentUser, image ,newUserData);

        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(new UsernamePasswordAuthenticationToken(
                updatedUser,
                updatedUser.getPassword(),
                updatedUser.getAuthorities()
        ));

        SecurityContextHolder.setContext(newContext);
        request.getSession(true).setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                newContext
        );

        model.addAttribute("username", updatedUser.getUsername());
        model.addAttribute("reload", "Algunos cambios podrían tardar en visualizarse, vuelve mas tarde");

        return "settings";
    }

    @GetMapping(value = "/admin/admin_panel")
    public String showAdminPanel(Model model) {
        log.info("Loading admin panel");

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("roles", Role.values());

        List<Map<String, Object>> users = userService.findAllUsers().stream()
                .map(user -> Map.of(
                        "user", user,
                        "image", user.image() != null
                                ? "data:image/png;base64," +Base64.getEncoder().encodeToString(user.image())
                                : ""
                ))
                .toList();
        model.addAttribute("users", users);

        return "admin_panel";
    }

    @PostMapping(value = "/admin/admin_panel/delete")
    public String deleteUserFromAdminPanel(@ModelAttribute("User") UserInfoDTO userInfoDTO, Model model) {

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("roles", Role.values());
        userService.deleteUser(userInfoDTO);

        List<Map<String, Object>> users = userService.findAllUsers().stream()
                .map(user -> Map.of(
                        "user", user,
                        "image", user.image() != null
                                ? "data:image/png;base64," +Base64.getEncoder().encodeToString(user.image())
                                : ""
                ))
                .toList();
        model.addAttribute("users", users);

        return "admin_panel";
    }

    @PostMapping(value = "admin/admin_panel/update")
    public String updateUser(@RequestParam(value = "oldUsername")String oldUserName, @ModelAttribute("newUser") UserInfoDTO newUserInfo, @RequestParam(value = "newImage", required = false) MultipartFile image, Model model) {

        log.info("Admin Updating user {}, to {}", oldUserName, newUserInfo);

        model.addAttribute("title", "Admin Panel");
        model.addAttribute("roles", Role.values());

        userService.updateUser(oldUserName, image, newUserInfo);

        List<Map<String, Object>> users = userService.findAllUsers().stream()
                .map(user -> Map.of(
                        "user", user,
                        "image", user.image() != null
                                ? "data:image/png;base64," +Base64.getEncoder().encodeToString(user.image())
                                : ""
                ))
                .toList();
        model.addAttribute("users", users);

        return "admin_panel";
    }
}
