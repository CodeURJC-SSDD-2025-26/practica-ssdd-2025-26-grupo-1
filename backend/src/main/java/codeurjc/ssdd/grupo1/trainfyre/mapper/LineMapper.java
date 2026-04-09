package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LineMapper {

    Line toLine(LineDTO lineDto);

    LineDTO toLineDto(Line line);
}
