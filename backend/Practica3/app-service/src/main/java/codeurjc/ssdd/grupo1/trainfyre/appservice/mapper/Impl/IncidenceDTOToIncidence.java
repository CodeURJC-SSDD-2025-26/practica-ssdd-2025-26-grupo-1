package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidenceDTOToIncidence implements Mapper<IncidenceDTO, Incidence> {
    @Override
    public Incidence map(IncidenceDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}
