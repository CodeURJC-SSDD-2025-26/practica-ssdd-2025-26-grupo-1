package codeurjc.ssdd.grupo1.trainfyre.dto;

import jakarta.persistence.EnumeratedValue;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum INCIDENCE_TYPE {
    MAINTENANCE(1), ACCIDENT(2), OTHERS(3);

    @EnumeratedValue
    private final int value;
}
