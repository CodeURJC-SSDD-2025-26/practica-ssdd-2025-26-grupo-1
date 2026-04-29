package codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.Role;

public record UserDTO(

        Long id,
        String username,
        String password,
        String email,
        Role role
) {}