package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.AlertRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.AlertMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.AlertService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService{

    private AlertRepository alertRepository;
    private AlertMapper alertMapper;
    private UserRepository userRepository;

    @Transactional
    public AlertDTO registerAlert(AlertRegistrationDTO alertrDTO, AppUser appUser) {

        Alert alert = new Alert();
        alert.setLine(alertrDTO.line());
        alert.setStartDate(alertrDTO.startDate());
        alert.setEndDate(alertrDTO.endDate());
        alert.setStartHour(alertrDTO.startHour());
        alert.setEndHour(alertrDTO.endHour());
        alert.setUser(appUser);

        alert.setUser(appUser);

        alertRepository.save(alert);

        return alertRepository.findByLineAndStartDateAndEndDate(alertrDTO.line(), alertrDTO.startDate(), alertrDTO.endDate())
                .map(alertMapper::alertToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al registrarse: "));
                
    }
}