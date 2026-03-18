package es.urjc.ssdd.grupo1.trainfyre.demo.backcore.dto;

import jakarta.persistence.EnumeratedValue;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum INCIDENCE_LEVEL {
    LOW(1), MEDIUM(2), HIGH(3);

    @EnumeratedValue
    private final int value;
}
