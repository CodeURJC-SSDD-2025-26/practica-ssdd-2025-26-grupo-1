package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidenceToIncidenceDTO implements Mapper<Incidence, IncidenceDTO> {
    @Override
    public IncidenceDTO map(Incidence input) {
        return new ModelMapper().map(input, IncidenceDTO.class);
    }
}
