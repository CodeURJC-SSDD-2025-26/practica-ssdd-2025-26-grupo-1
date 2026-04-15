package codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs;

import jakarta.persistence.EnumeratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum INCIDENCE_STATUS {
    CLOSED(0), ACTIVE(1);

    @EnumeratedValue
    private final int value;
}
