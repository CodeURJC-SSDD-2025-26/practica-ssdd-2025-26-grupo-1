package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository.AlertRepository;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.AlertMapper;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.AlertService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private AlertRepository alertRepository;
    private AlertMapper alertMapper;
    private UserMapper userMapper;

    @Transactional
    public AlertDTO registerAlert(AlertRegistrationDTO alertrDTO, UserDTO appUser) {

        Alert alert = new Alert();
        alert.setLine(alertrDTO.line());
        alert.setStartDate(alertrDTO.startDate());
        alert.setEndDate(alertrDTO.endDate());
        alert.setStartHour(alertrDTO.startHour());
        alert.setEndHour(alertrDTO.endHour());
        alert.setUser(userMapper.userDTOToAppUser(appUser));

        alertRepository.save(alert);

        return alertRepository
                .findByLineAndStartDateAndEndDate(alertrDTO.line(), alertrDTO.startDate(), alertrDTO.endDate())
                .map(alertMapper::alertToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al registrarse: "));

    }

    @Transactional
    public AlertDTO updateAlert(AlertDTO alertrDTO) {

        Alert currentAlert;
        Optional<Alert> currentAlertO = alertRepository.findById(alertrDTO.id());

        if (!currentAlertO.isPresent()) {
            throw (new UsernameNotFoundException("Error al actualizar: "));
        }

        currentAlert = currentAlertO.get();
        currentAlert.setLine(alertrDTO.line());
        currentAlert.setStartDate(alertrDTO.startDate());
        currentAlert.setEndDate(alertrDTO.endDate());
        currentAlert.setStartHour(alertrDTO.startHour());
        currentAlert.setEndHour(alertrDTO.endHour());

        alertRepository.save(currentAlert);

        return alertRepository
                .findByLineAndStartDateAndEndDate(alertrDTO.line(), alertrDTO.startDate(), alertrDTO.endDate())
                .map(alertMapper::alertToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al actualizar: "));

    }

    @Transactional
    public void deleteAlert(Long id) {
        Alert alertToDelete = alertRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró la incidencia con ID: " + id));

        // Delete dependencies.
        alertToDelete.setUser(null);
        alertToDelete.setLine(null);

        // Delete alert.
        alertRepository.delete(alertToDelete);
    }

    public Page<AlertDTO> getPage(UserDTO user, Pageable page) {
        return alertRepository.findByUserOrderByLine(userMapper.userDTOToAppUser(user), page)
                .map(alert -> alertMapper.alertToDTO(alert));
    }

    public AlertDTO getAlert(long id) {
        return alertRepository.findById(id)
                .map(alertMapper::alertToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al obtener la alerta: "));

    }

    public Boolean isValidDate(String start, String end) {
        String[] startDay = start.split("-");
        String[] endDay = end.split("-");
        LocalDate startDate;
        LocalDate endDate;

        startDate = LocalDate.of(Integer.parseInt(startDay[0]), Integer.parseInt(startDay[1]),
                Integer.parseInt(startDay[2]));
        endDate = LocalDate.of(Integer.parseInt(endDay[0]), Integer.parseInt(endDay[1]), Integer.parseInt(endDay[2]));

        return (end.compareTo(start) >= 0);
    }
}