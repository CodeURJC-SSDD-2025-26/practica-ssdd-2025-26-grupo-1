package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidenceRegistrationDTOToIncidence implements Mapper<IncidenceRegistrationDTO, Incidence> {
    @Override
    public Incidence map(IncidenceRegistrationDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}

