package codeurjc.ssdd.grupo1.trainfyre.dto;

public record UserRegistrationtDTO(

        String username,
        String password,
        String email,
        Role role,
        byte[] image
){}
