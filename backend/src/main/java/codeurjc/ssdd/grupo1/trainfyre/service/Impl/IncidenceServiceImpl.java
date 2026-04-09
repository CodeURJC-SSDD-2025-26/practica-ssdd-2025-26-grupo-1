package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.IncidenceRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceRegistrationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.IncidenceMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import lombok.AllArgsConstructor;
import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncidenceServiceImpl implements IncidenceService {
    private IncidenceRepository incidenceRepository;
    private IncidenceRepository lineRepository;
    private IncidenceMapper incidenceMapper;

    @Transactional
    public IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO) {
        // TODO
        Incidence incidence = new Incidence();

        incidence.setIncidenceLevel(incidenceRegistrationDTO.incidenceLevel());
        incidence.setDescription(incidenceRegistrationDTO.description());
        incidence.setDate(incidenceRegistrationDTO.date());
        incidence.setStatus(incidenceRegistrationDTO.status());
        incidence.setImage(incidenceRegistrationDTO.image());
        incidence.setAffectedLines(null);
        // List<Line> lines = lineRepository.findAllByName(incidenceRegistrationDTO.affectedLines());
        // incidence.setAffectedLines(lines);

        return incidenceRepository.findById(incidence.getId())
                .map(incidenceMapper::toIncidenceDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al crear una incidencia"));
    }

    @Transactional
    public IncidenceDTO updateIncidence(Long id, IncidenceRegistrationDTO incidenceRegistrationDTO) {
        // TODO
        return null;
    }

    @Transactional
    public void deleteIncidence(Long id) {
        // TODO
    }

    @Override
    public List<IncidenceDTO> getAllIncidences() {
        return this.incidenceRepository.findAll().stream()
                .map(this.incidenceMapper::toIncidenceDTO)
                .toList();
    }
}
