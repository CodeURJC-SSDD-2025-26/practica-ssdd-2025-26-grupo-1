package codeurjc.ssdd.grupo1.trainfyre.appservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record EmailRequest(
        @NotEmpty List<@Email String> to,
        @NotBlank String subject,
        @NotBlank String body
) { }
