package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceRegistrationDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidenceMapper {
    Incidence toIncidence(IncidenceDTO incidenceDTO);

    IncidenceDTO toIncidenceDTO(Incidence incidence);

    Incidence incidenceRegistrationDTOtoIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
}
