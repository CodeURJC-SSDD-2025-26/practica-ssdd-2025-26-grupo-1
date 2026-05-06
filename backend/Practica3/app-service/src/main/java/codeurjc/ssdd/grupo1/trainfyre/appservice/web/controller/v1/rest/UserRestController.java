package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Gets an specified user by id")
    public UserInfoDTO getUser(@PathVariable("id") Long id){
        return userService.findUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Creates a new user")
    public UserInfoDTO postUser(@RequestBody UserDTO newUser){
        return userService.createUser(newUser);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Updates an specified user")
    public UserInfoDTO putUser(@RequestBody Long id, @RequestBody UserDTO newUser){
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Deletes an specified user")
    public UserInfoDTO deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
