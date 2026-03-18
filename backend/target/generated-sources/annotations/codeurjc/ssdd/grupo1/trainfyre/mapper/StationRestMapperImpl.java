package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Station;
import codeurjc.ssdd.grupo1.trainfyre.dto.StationDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-18T09:48:56+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class StationRestMapperImpl implements StationRestMapper {

    @Override
    public Station toStation(StationDTO stationDto) {
        if ( stationDto == null ) {
            return null;
        }

        Station station = new Station();

        station.setId( stationDto.id() );
        station.setName( stationDto.name() );
        station.setAddress( stationDto.address() );

        return station;
    }

    @Override
    public StationDTO toStationDto(Station station) {
        if ( station == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String address = null;

        id = station.getId();
        name = station.getName();
        address = station.getAddress();

        StationDTO stationDTO = new StationDTO( id, name, address );

        return stationDTO;
    }
}
