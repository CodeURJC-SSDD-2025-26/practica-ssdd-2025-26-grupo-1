package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidenceRegistrationDTO;

import java.util.List;

public interface IncidenceService {
    IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
    IncidenceDTO updateIncidence(Long id, IncidenceRegistrationDTO incidenceRegistrationDTO);
    void deleteIncidence(Long id);

    List<IncidenceDTO> getAllIncidences();
    String generateHeatmapJSON();
}
