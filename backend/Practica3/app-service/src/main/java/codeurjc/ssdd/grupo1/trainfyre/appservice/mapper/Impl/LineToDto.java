package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LineToDto implements Mapper<Object, LineDTO> {

    @Override
    public LineDTO map(Object input) {
        return new ModelMapper().map(input, LineDTO.class);
    }
}
