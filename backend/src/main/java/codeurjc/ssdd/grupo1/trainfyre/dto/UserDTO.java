package codeurjc.ssdd.grupo1.trainfyre.dto;

public record UserDTO(

        Long id,
        String username,
        String password,
        String email,
        Role role
) {}