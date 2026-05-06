package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.ImagenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Image-Controller", description = "Servicios de visualizacion de imagenes")
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImagenService imagenService;

    @GetMapping("/{id}")
    @Operation(description = "Get image by id")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImageDTO imageDTO = imagenService.getImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"image-" + id + "\"")
                .body(imageDTO.image());
    }
}
