package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.StationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StationToDto implements Mapper<Object, StationDTO> {

    @Override
    public StationDTO map(Object input) {
        return new ModelMapper().map(input, StationDTO.class);
    }
}
