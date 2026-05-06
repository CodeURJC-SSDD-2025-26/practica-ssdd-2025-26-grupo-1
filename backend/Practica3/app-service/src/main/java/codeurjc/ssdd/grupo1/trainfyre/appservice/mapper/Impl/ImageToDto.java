package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.ImageDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageToDto implements Mapper<Object, ImageDTO> {

    @Override
    public ImageDTO map(Object input) {
        return new ModelMapper().map(input, ImageDTO.class);
    }
}
