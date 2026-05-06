package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Image;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageDtoToImage implements Mapper<ImageDTO, Image> {

    @Override
    public Image map(ImageDTO input) {
        return new ModelMapper().map(input, Image.class);
    }
}
