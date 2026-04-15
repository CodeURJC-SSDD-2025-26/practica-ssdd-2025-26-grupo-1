package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Station;
import codeurjc.ssdd.grupo1.trainfyre.dto.StationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationRestMapper {

    Station toStation(StationDTO stationDto);

    StationDTO toStationDto(Station station);
}
