package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlertToDto implements Mapper<Alert, AlertDTO> {
    @Override
    public AlertDTO map(Alert input) {
        return new ModelMapper().map(input, AlertDTO.class);
    }
}