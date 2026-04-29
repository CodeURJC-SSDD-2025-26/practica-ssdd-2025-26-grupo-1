package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidenceRegistrationDTOToIncidence implements Mapper<IncidenceRegistrationDTO, Incidence> {
    @Override
    public Incidence map(IncidenceRegistrationDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}

