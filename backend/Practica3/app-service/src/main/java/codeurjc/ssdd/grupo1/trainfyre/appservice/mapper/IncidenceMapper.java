package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceIndexDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceRegistrationDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidenceMapper {
    Incidence toIncidence(IncidenceDTO incidenceDTO);
    Incidence toIncidence(IncidenceIndexDTO incidenceIndexDTO);

    IncidenceDTO toIncidenceDTO(Incidence incidence);

    Incidence incidenceRegistrationDTOtoIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
}
