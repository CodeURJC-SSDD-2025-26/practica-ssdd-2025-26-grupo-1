package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Tag(name = "User-Controller", description = "Servicios de gestión y configuración de Usuarios")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Gets all users")
    public List<UserInfoDTO> getUsers() {
        return userService.findAllUsers();
    }

    /*
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Gets an specified user by id")
    public UserInfoDTO getUser(@PathVariable("id") Long id){

    }*/

}
