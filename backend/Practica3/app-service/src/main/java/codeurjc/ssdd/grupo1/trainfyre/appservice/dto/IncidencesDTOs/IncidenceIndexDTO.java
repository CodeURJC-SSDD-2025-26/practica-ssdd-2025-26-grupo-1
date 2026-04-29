package codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;

import java.time.LocalDateTime;
import java.util.List;

public record IncidenceIndexDTO (
    String incidenceID,
    String description,
    LocalDateTime date,
    List<Line> affectedLines
) {}
