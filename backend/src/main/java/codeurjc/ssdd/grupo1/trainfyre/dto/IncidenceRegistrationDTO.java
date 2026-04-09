package codeurjc.ssdd.grupo1.trainfyre.dto;

import java.time.LocalDateTime;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;

import java.util.List;

public record IncidenceRegistrationDTO (
    INCIDENCE_LEVEL incidenceLevel,
    String description,
    LocalDateTime date,
    INCIDENCE_STATUS status,
    byte[] image,
    List<String> affectedLines
) {}
