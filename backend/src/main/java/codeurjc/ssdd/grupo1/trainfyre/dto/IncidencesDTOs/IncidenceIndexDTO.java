package codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs;

import java.time.LocalDateTime;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;

import java.util.List;

public record IncidenceIndexDTO (
    String incidenceID,
    String description,
    LocalDateTime date,
    List<Line> affectedLines
) {}
