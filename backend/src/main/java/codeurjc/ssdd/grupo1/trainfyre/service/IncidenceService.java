package codeurjc.ssdd.grupo1.trainfyre.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidenceService {
    IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO);
    void updateIncidence(MultipartFile updatedImage, IncidenceRegistrationDTO incidenceRegistrationDTO);
    void deleteIncidence(String incidenceID);

    IncidenceDTO getIncidenceWithID(String incidenceID);
    Page<Incidence> findAll(Pageable pageable);
    List<IncidenceDTO> getAllIncidences();
    List<IncidenceDTO> getAllIncidencesAffectingLineAsDTO(Line line);
    List<Incidence> getAllIncidencesAffectingLine(Line line);
    String generatePieChartJSON();
    String generateHeatmapJSON();
}
