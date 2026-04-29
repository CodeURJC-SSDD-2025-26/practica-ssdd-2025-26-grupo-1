package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;


import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LineDtoToLine implements Mapper<LineDTO, Line> {

    @Override
    public Line map(LineDTO input) {
        return new ModelMapper().map(input, Line.class);
    }
}
