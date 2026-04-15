package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlertDtoToAlert implements Mapper<AlertDTO, Alert> {
    @Override
    public Alert map(AlertDTO input) {
        return new ModelMapper().map(input, Alert.class);
    }
}