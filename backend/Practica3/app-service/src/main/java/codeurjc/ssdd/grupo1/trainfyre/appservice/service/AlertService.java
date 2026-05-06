package codeurjc.ssdd.grupo1.trainfyre.appservice.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertShowDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;

public interface AlertService {

    AlertDTO registerAlert(AlertRegistrationDTO alertRegistrationtDTO, UserDTO user);

    public void deleteAlert(Long id);

    public ResponseEntity<AlertShowDTO> deleteAlertRest(Long id);

    public AlertDTO updateAlert(AlertDTO alertrDto);

    public ResponseEntity<AlertShowDTO> updateAlert(UserDTO user, Long id, AlertRegistrationDTO alertrDto);

    public Page<AlertDTO> getPage(UserDTO user, Pageable page); 

    public Page<AlertDTO> getPageNUser(Pageable page);

    public AlertDTO getAlert(long id);

    public Boolean isValidDate(String start, String end);

    public Boolean isValidTimeRange(String start, String end);
}