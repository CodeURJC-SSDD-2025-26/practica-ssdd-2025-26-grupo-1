package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;


import codeurjc.ssdd.grupo1.trainfyre.data.model.Station;
import codeurjc.ssdd.grupo1.trainfyre.dto.StationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StationDtoToStation implements Mapper<StationDTO, Station> {

    @Override
    public Station map(StationDTO input) {
        return new ModelMapper().map(input, Station.class);
    }
}
