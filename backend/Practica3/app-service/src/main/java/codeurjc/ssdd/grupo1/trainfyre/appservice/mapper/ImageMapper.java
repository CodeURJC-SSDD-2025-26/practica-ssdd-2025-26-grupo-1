package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Image;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDTO imageDTO);

    ImageDTO toImageDTO(Image image);
}
