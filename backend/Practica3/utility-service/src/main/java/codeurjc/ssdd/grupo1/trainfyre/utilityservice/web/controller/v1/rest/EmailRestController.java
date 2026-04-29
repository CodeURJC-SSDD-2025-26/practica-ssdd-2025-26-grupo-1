package codeurjc.ssdd.grupo1.trainfyre.utilityservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.utilityservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

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
//    @PreAuthorize(RolesEnum.ALL_ROLES_ACCESS)
//    public Mono<SingleResponse<GdocSiteResponse>> createSite(@Valid @RequestBody SiteCreateRequest request) {
//        return siteRestFacade.createSite(request);
//    }
    public Void sendEmail() {
        return null;
    }

}
