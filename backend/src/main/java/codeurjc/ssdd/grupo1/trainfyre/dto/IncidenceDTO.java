package codeurjc.ssdd.grupo1.trainfyre.dto;

import java.time.LocalDateTime;

public record IncidenceDTO (
    Long id,
    INCIDENCE_LEVEL incidenceLevel,
    String description,
    LocalDateTime date,
    INCIDENCE_STATUS status
) {}
