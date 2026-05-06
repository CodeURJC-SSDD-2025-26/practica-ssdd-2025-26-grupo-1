package codeurjc.ssdd.grupo1.trainfyre.appservice.web.controller.v1.rest;


import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineCreateRequestDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineUpdateRequestDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.LineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Tag(name = "Line-Controller", description = "Servicios de gestión y configuración de lineas")
@RequestMapping("/api/v1/lines")
@RequiredArgsConstructor
public class LineRestController {	
    private final LineService lineService;

    @GetMapping 
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get all lines")
    public List<LineDTO> getAllLines() {
		return lineService.getAllLines();
    }
	
    @GetMapping (value = "/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get line by name")
    public LineDTO getLineByName(@Valid @PathVariable @Parameter(description = "Nombre linea", required = true) String name) {
		return lineService.getLineByName(name);
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "add line")
	public LineDTO addLine(@RequestBody LineCreateRequestDTO req) {
		return lineService.addLine(req.name(), req.description(), req.color());
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "update line")
	public LineDTO updateLine(@RequestBody LineUpdateRequestDTO req) {
		return lineService.updateLine(req.oldName(), req.newName(), req.description(), req.color());
	}

	@DeleteMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "delete line")
	public void deleteLine(@Valid @PathVariable @Parameter(description = "Nombre linea", required = true) String name) {
		lineService.deleteLine(name);
	}
}
