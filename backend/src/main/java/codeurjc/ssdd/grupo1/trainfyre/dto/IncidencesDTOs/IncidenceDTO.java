package codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs;

import java.time.LocalDateTime;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;

import java.util.List;

public record IncidenceDTO (
    Long id,
    String incidenceID,
    INCIDENCE_LEVEL incidenceLevel,
    INCIDENCE_TYPE incidenceType,
    String description,
    LocalDateTime date,
    INCIDENCE_STATUS status,
    byte[] image,
    List<Line> affectedLines
) {}
