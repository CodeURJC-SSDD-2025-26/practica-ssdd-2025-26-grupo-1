package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;


import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Station;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.StationDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StationDtoToStation implements Mapper<StationDTO, Station> {

    @Override
    public Station map(StationDTO input) {
        return new ModelMapper().map(input, Station.class);
    }
}
