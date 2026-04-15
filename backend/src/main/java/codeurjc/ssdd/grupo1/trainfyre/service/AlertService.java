package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;

public interface AlertService {

    AlertDTO registerAlert(AlertRegistrationDTO alertRegistrationtDTO, AppUser user);

    public void deleteAlert(Long id);

    public AlertDTO updateAlert(AlertDTO alertrDto);
}