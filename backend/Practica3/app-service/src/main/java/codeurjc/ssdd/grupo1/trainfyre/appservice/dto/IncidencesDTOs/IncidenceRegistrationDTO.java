package codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;

import java.time.LocalDateTime;
import java.util.List;

public record IncidenceRegistrationDTO (
    String incidenceID,
    INCIDENCE_LEVEL incidenceLevel,
    INCIDENCE_TYPE incidenceType,
    String description,
    LocalDateTime date,
    INCIDENCE_STATUS status,
    byte[] image,
    List<Line> affectedLines
) {}
