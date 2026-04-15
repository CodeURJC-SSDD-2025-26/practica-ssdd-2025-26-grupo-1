package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlertToDto implements Mapper<Alert, AlertDTO> {
    @Override
    public AlertDTO map(Alert input) {
        return new ModelMapper().map(input, AlertDTO.class);
    }
}