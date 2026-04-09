package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.repository.LineRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LineLineImpl implements LineService {
    private LineRepository repository;
    private LineMapper LineRestMapper;

    @Override
    public List<LineDTO> getAllLines() {
        return this.repository.findAll().stream()
                .map(this.LineRestMapper::toLineDto)
                .toList();
    }
}
