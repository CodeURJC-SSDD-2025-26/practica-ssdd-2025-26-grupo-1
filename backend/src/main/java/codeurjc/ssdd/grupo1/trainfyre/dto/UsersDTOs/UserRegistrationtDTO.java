package codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs;

import codeurjc.ssdd.grupo1.trainfyre.dto.Role;

public record UserRegistrationtDTO(

        String username,
        String password,
        String email,
        Role role
){}
