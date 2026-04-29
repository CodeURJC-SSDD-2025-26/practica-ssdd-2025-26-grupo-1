package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Station;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.StationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationRestMapper {

    Station toStation(StationDTO stationDto);

    StationDTO toStationDto(Station station);
}
