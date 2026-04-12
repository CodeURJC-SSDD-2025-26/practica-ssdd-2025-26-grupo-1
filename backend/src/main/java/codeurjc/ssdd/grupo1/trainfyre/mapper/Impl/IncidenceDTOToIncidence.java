package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidenceDTOToIncidence implements Mapper<IncidenceDTO, Incidence> {
    @Override
    public Incidence map(IncidenceDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}
