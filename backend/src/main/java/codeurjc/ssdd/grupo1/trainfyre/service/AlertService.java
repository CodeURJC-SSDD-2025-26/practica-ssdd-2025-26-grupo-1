package codeurjc.ssdd.grupo1.trainfyre.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserDTO;

public interface AlertService {

    AlertDTO registerAlert(AlertRegistrationDTO alertRegistrationtDTO, UserDTO user);

    public void deleteAlert(Long id);

    public AlertDTO updateAlert(AlertDTO alertrDto);

    public Page<AlertDTO> getPage(UserDTO user, Pageable page); 

    public AlertDTO getAlert(long id);

    public Boolean isValidDate(String start, String end);
}