package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import org.springframework.stereotype.Component;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertShowDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.AlertMapper;

@Component
public class AlertMapperShow{
    public AlertShowDTO alertToShowDTO(Alert alert) {
        return new AlertShowDTO(alert.getId(), alert.getLine().getName(), alert.getStartDate(), alert.getEndDate(), alert.getStartHour(), alert.getEndHour());
    }
}
