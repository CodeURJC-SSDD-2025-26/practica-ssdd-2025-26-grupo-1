package codeurjc.ssdd.grupo1.trainfyre.utilityservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.utilityservice.dto.EmailRequest;
import codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(name = "Email", description = "Servicios de gestión y configuración de Emails")
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
public class EmailRestController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Enviar un Email.")
    public void  sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.to().toArray(new String[0]), emailRequest.subject(), emailRequest.body());
    }
}
