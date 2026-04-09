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
    private final byte[] defaultProfileImage = loadDefaultProfileImage();

    protected static final byte[] defaultLogoImage = loadDefaultLogoImage();

    protected static final byte[] defaultIncidenceImage = loadDefaultIncidenceImage();

    private byte[] loadDefaultProfileImage() {
        try {
            return new ClassPathResource("static/img/user.png")
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            log.error("Error cargando la imagen de perfil por defecto: {}", e.getMessage());
            return new byte[0];
        }
    }

    private byte[] loadDefaultLogoImage() {
        try {
            return new ClassPathResource("static/img/logo.jpg")
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            log.error("Error cargando el logo de la web: {}", e.getMessage());
            return new byte[0];
        }
    }

    private byte[] loadDefaultIncidenceImage() {
        try {
            return new ClassPathResource("static/img/incidence_default.jpg")
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            log.error("Error cargando la imagen de incidencia por defecto: {}", e.getMessage());
            return new byte[0];
        }
    }
}