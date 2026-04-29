package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.AlertDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlertDtoToAlert implements Mapper<AlertDTO, Alert> {
    @Override
    public Alert map(AlertDTO input) {
        return new ModelMapper().map(input, Alert.class);
    }
}