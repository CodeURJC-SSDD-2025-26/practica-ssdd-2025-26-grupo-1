package codeurjc.ssdd.grupo1.trainfyre.dto;

import jakarta.persistence.EnumeratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ANNONYMOUS(0), REGISTERED(1), ADMIN(2);

    @EnumeratedValue
    private final int value;
}
