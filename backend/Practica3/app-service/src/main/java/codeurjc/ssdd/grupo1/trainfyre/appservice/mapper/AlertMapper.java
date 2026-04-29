package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertRegistrationDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AlertMapper {

    Alert alertDTOToAlert(AlertDTO userDTO);

    Alert alertRegistrationDTOtoAlert(AlertRegistrationDTO alertRegistrationtDTO);

    AlertDTO alertToDTO(Alert alert);
}