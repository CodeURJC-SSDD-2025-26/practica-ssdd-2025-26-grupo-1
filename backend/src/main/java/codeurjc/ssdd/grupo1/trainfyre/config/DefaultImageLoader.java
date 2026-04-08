package codeurjc.ssdd.grupo1.trainfyre.config;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

@Slf4j
@UtilityClass
public class DefaultImageLoader {

    @Getter(lazy = true)
    private final byte[] defaultImage = loadDefaultImage();

    private byte[] loadDefaultImage() {
        try {
            return new ClassPathResource("static/img/user.png")
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            log.error("Error cargando la imagen por defecto: {}", e.getMessage());
            return new byte[0];
        }
    }
}
