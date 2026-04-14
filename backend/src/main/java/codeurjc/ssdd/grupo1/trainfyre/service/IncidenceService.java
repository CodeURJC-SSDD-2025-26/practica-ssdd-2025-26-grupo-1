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
    void updateIncidence(Long id, MultipartFile updatedImage, IncidenceRegistrationDTO incidenceRegistrationDTO);
    void deleteIncidence(Long id);

    Page<Incidence> findAll(Pageable pageable);
    List<IncidenceDTO> getAllIncidences();
    List<Incidence> getAllIncidencesWithID();
    List<Incidence> getAllIncidencesAffectingLine(Line line);
    String generatePieChartJSON();
    String generateHeatmapJSON();
}
