package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.repository.IncidenceRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.IncidenceMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncidenceServiceImpl implements IncidenceService {
    private IncidenceRepository repository;
    private IncidenceMapper incidenceMapper;

    @Override
    public List<IncidenceDTO> getAllIncidences() {
        return this.repository.findAll().stream()
                .map(this.incidenceMapper::toIncidenceDTO)
                .toList();
    }
}
