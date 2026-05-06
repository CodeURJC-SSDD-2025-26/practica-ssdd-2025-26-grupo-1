package codeurjc.ssdd.grupo1.trainfyre.appservice.config;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Image;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository.ImageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter
public class DefaultImages {

    private final ImageRepository imageRepository;

    private Long defaultProfileImageId;
    private Long defaultIncidenceImageId;
    private Long defaultLogoImageId;

    @EventListener(ApplicationReadyEvent.class)
    public void initDefaultImages() {
        log.info("Initializing default images in database...");

        Image profileImage = new Image();
        profileImage.setImage(DefaultImageLoader.getDefaultProfileImage());
        profileImage = imageRepository.save(profileImage);
        defaultProfileImageId = profileImage.getId();
        log.info("Default profile image created with id: {}", defaultProfileImageId);

        Image incidenceImage = new Image();
        incidenceImage.setImage(DefaultImageLoader.getDefaultIncidenceImage());
        incidenceImage = imageRepository.save(incidenceImage);
        defaultIncidenceImageId = incidenceImage.getId();
        log.info("Default incidence image created with id: {}", defaultIncidenceImageId);

        Image logoImage = new Image();
        logoImage.setImage(DefaultImageLoader.defaultLogoImage);
        logoImage = imageRepository.save(logoImage);
        defaultLogoImageId = logoImage.getId();
        log.info("Default logo image created with id: {}", defaultLogoImageId);
    }
}