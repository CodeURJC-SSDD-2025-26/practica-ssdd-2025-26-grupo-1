package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LineMapper {

    Line toLine(LineDTO lineDto);

    LineDTO toLineDto(Line line);

    default String mapLineToString(LineDTO line) {
        if (line == null) {
            return null;
        }
        return line.name();
    }
}
