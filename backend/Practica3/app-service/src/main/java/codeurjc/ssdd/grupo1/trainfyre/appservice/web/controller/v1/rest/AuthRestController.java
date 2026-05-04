package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.security.jwt.AuthResponse;
import codeurjc.ssdd.grupo1.trainfyre.appservice.security.jwt.LoginRequest;
import codeurjc.ssdd.grupo1.trainfyre.appservice.security.jwt.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserLoginService userLoginService;

    public AuthRestController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            HttpServletResponse response,
            @RequestBody LoginRequest loginRequest) {
        return userLoginService.login(response, loginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            HttpServletResponse response,
            @CookieValue("refreshToken") String refreshToken) {
        return userLoginService.refresh(response, refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        return ResponseEntity.ok(userLoginService.logout(response));
    }
}