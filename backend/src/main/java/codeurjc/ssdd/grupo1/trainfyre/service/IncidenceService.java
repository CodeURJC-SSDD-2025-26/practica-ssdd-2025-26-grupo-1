package codeurjc.ssdd.grupo1.trainfyre.service;

import java.util.List;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceRegistrationDTO;

public interface IncidenceService {
    IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
    IncidenceDTO updateIncidence(Long id, IncidenceRegistrationDTO incidenceRegistrationDTO);
    void deleteIncidence(Long id);

    List<IncidenceDTO> getAllIncidences();
    String generatePieChartJSON();
    String generateHeatmapJSON();
}
