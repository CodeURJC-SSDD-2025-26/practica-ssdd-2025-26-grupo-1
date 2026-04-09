package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AlertMapper {

    Alert alertDTOToAlert(AlertDTO userDTO);

    Alert alertRegistrationDTOtoAlert(AlertRegistrationDTO alertRegistrationtDTO);

    AlertDTO alertToDTO(Alert alert);
}