package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LineToDto implements Mapper<Object, LineDTO> {

    @Override
    public LineDTO map(Object input) {
        return new ModelMapper().map(input, LineDTO.class);
    }
}
