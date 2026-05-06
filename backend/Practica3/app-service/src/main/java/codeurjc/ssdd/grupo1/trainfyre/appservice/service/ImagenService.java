package codeurjc.ssdd.grupo1.trainfyre.appservice.service;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;

public interface ImagenService {

    ImageDTO createImage(ImageDTO imageDTO);

    ImageDTO getImage(Long id);

    void deleteImage(Long id);
}
