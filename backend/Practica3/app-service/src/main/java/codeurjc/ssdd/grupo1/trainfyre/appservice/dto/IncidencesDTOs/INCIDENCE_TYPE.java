package codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs;

import jakarta.persistence.EnumeratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum INCIDENCE_TYPE {
    MAINTENANCE(1), ACCIDENT(2), OTHERS(3);

    @EnumeratedValue
    private final int value;
}
