package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceIndexDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IndicenceIndexDTOtoIncidence  implements Mapper<IncidenceIndexDTO, Incidence> {
    @Override
    public Incidence map(IncidenceIndexDTO input) {
        return new ModelMapper().map(input, Incidence.class);
    }
}
