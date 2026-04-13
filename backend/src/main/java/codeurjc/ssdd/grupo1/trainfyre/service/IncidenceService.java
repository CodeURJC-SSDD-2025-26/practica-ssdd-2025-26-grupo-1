package codeurjc.ssdd.grupo1.trainfyre.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceRegistrationDTO;

public interface IncidenceService {
    IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
    void updateIncidence(Long id, MultipartFile updatedImage, IncidenceRegistrationDTO incidenceRegistrationDTO);
    void deleteIncidence(Long id);

    List<IncidenceDTO> getAllIncidences();
    String generatePieChartJSON();
    String generateHeatmapJSON();
}
