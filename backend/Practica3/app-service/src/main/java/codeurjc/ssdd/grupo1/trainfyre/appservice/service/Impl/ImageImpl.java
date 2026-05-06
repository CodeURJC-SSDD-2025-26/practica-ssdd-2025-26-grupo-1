package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Image;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository.ImageRepository;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.ImageMapper;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.ImagenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ImageImpl implements ImagenService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;

    @Override
    public ImageDTO createImage(ImageDTO imageDTO) {
        if (imageDTO == null) {
            return null;
        }
        Image image = imageMapper.toImage(imageDTO);
        Image savedImage = imageRepository.save(image);
        return imageMapper.toImageDTO(savedImage);
    }

    @Override
    public ImageDTO getImage(Long id) {
        return imageRepository.findById(id)
                .map(imageMapper::toImageDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró la imagen con ID: " + id
                ));
    }

    @Override
    public void deleteImage(Long id) {
        Image imageToDelete = imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró la imagen con ID: " + id
                ));
        imageRepository.delete(imageToDelete);
    }
}
