package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceIndexDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IndicenceIndexDTOtoIncidence  implements Mapper<IncidenceIndexDTO, Incidence> {
    @Override
    public Incidence map(IncidenceIndexDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}
