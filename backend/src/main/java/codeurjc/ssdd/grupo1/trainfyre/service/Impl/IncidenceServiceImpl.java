package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.IncidenceRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.*;
import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.IncidenceMapper;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
import lombok.AllArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncidenceServiceImpl implements IncidenceService {
    private IncidenceRepository incidenceRepository;
    private IncidenceMapper incidenceMapper;
    private LineMapper lineMapper;
    private ObjectMapper jsonMapper;
    private LineService lineService;

    @Transactional
    public IncidenceDTO createIncidence(IncidenceRegistrationDTO incidenceRegistrationDTO) {
        Incidence incidence = new Incidence();

        incidence.setIncidenceID(incidenceRegistrationDTO.incidenceID());
        incidence.setIncidenceLevel(incidenceRegistrationDTO.incidenceLevel());
        incidence.setIncidenceType(incidenceRegistrationDTO.incidenceType());
        incidence.setDescription(incidenceRegistrationDTO.description());
        incidence.setDate(incidenceRegistrationDTO.date());
        incidence.setStatus(incidenceRegistrationDTO.status());
        incidence.setImage(incidenceRegistrationDTO.image());
        incidence.setAffectedLines(incidenceRegistrationDTO.affectedLines());

        Incidence incidenceSaved = incidenceRepository.save(incidence);

        return incidenceMapper.toIncidenceDTO(incidenceSaved);
    }

    @Transactional
    public void updateIncidence(MultipartFile updatedImage, IncidenceRegistrationDTO incidenceRegistrationDTO) {
        Incidence incidenceToUpdate = incidenceRepository.findByIncidenceID(incidenceRegistrationDTO.incidenceID())
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Incidencia no encontrada: " + incidenceRegistrationDTO.incidenceID()));

        if (incidenceRegistrationDTO.incidenceLevel() != null) {
            incidenceToUpdate.setIncidenceLevel(incidenceRegistrationDTO.incidenceLevel());
        }

        if (incidenceRegistrationDTO.incidenceType() != null) {
            incidenceToUpdate.setIncidenceType(incidenceRegistrationDTO.incidenceType());
        }

        if (incidenceRegistrationDTO.description() != null && !incidenceRegistrationDTO.description().isBlank()) {
            incidenceToUpdate.setDescription(incidenceRegistrationDTO.description());
        }

        if (incidenceRegistrationDTO.status() != null) {
            incidenceToUpdate.setStatus(incidenceRegistrationDTO.status());
        }

        if (updatedImage != null && !updatedImage.isEmpty()) {
            byte[] imageData;
            try {
                imageData = updatedImage.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e + "error en la lectura del archivo");
            }
            incidenceToUpdate.setImage(imageData);
        }

        incidenceRepository.save(incidenceToUpdate);
    }

    @Transactional
    public void deleteIncidence(String incidenceID) {
        Incidence incidenceToDelete = incidenceRepository.findByIncidenceID(incidenceID)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la incidencia con ID: " + incidenceID
            ));

        incidenceRepository.delete(incidenceToDelete);
    }

    public IncidenceDTO getIncidenceWithID(String incidenceID) {
        return incidenceMapper.toIncidenceDTO(this.incidenceRepository.findByIncidenceID(incidenceID)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la incidencia con ID: " + incidenceID
            )));
    }

    public Page<Incidence> findAll(Pageable pageable) {
        return incidenceRepository.findAll(pageable);
    }

    @Override
    public List<IncidenceDTO> getAllIncidences() {
        return this.incidenceRepository.findAll().stream()
                .map(this.incidenceMapper::toIncidenceDTO)
                .toList();
    }

    @Override
    public List<IncidenceDTO> getAllIncidencesAffectingLineAsDTO(Line line) {
        return this.incidenceRepository.findByAffectedLinesContaining(line).stream()
                .map(this.incidenceMapper::toIncidenceDTO)
                .toList();
    }

    public List<Incidence> getAllIncidencesAffectingLine(Line line) {
        return this.incidenceRepository.findByAffectedLinesContaining(line);
    }

    public String generatePieChartJSON() {
        List<LineDTO> allLines = lineService.getAllLines();
        double allIncidencesCountingAllLines = allLines.stream()
            .mapToDouble(line -> getAllIncidencesAffectingLine(lineMapper.toLine(line)).size())
            .sum();
        List<PieChartInfo> data = new ArrayList<>();

        if (allIncidencesCountingAllLines > 0) {
            for (LineDTO line: allLines) {
                if (!getAllIncidencesAffectingLine(lineMapper.toLine(line)).isEmpty()) {
                    data.add(new PieChartInfo(line.name(),
                        Math.round((getAllIncidencesAffectingLine(lineMapper.toLine(line)).size() / allIncidencesCountingAllLines) * 100 * 100.0) / 100.0));
                }
            }

            return jsonMapper.writeValueAsString(data);
        }

        data.add(new PieChartInfo("No hay incidencias registradas", 100));

        return jsonMapper.writeValueAsString(data);
    }

    public String generateHeatmapJSON() {
        HeatmapInfo heatmap = new HeatmapInfo();
        List<LineDTO> allLines = lineService.getAllLines();
        int allLinesNumber = allLines.size();

        heatmap.setTitle("INCIDENCIAS SEMANALES");
        heatmap.setRows(allLinesNumber);
        heatmap.setColumns(7);

        heatmap.setRowLabels(allLines.stream().map(LineDTO::name).toList());

        heatmap.setRowColors(List.of(
            "rgb(50, 214, 255)", "green", "purple", "blue", "rgb(255, 230, 0)",
            "red", "grey", "brown", "greenyellow", "rgb(255, 111, 135)"
        ));

        heatmap.setColumnLabels(List.of(
            "Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"
        ));

        heatmap.setCells(generateHeatmapCells(allLines));

        return jsonMapper.writeValueAsString(heatmap);
    }

    private List<Cell> generateHeatmapCells(List<LineDTO> allLines) {
        List<Cell> cellList = new ArrayList<>();
        int i = 0;

        for (LineDTO line: allLines) {
            int mondayIncidences = 0;
            int tuesdayIncidences = 0;
            int wednesdayIncidences = 0;
            int thursdayIncidences = 0;
            int fridayIncidences = 0;
            int saturdayIncidences = 0;
            int sundayIncidences = 0;
            List<Incidence> allIncidences = getAllIncidencesAffectingLine(lineMapper.toLine(line));

            for (Incidence incidence: allIncidences) {
                switch (incidence.getDate().getDayOfWeek()) {
                    case MONDAY:
                        mondayIncidences++;
                        break;
                    case TUESDAY:
                        tuesdayIncidences++;
                        break;
                    case WEDNESDAY:
                        wednesdayIncidences++;
                        break;
                    case THURSDAY:
                        thursdayIncidences++;
                        break;
                    case FRIDAY:
                        fridayIncidences++;
                        break;
                    case SATURDAY:
                        saturdayIncidences++;
                        break;
                    case SUNDAY:
                        sundayIncidences++;
                        break;
                    default:
                        break;
                }
            }

            cellList.add(new Cell(i, mondayIncidences));
            cellList.add(new Cell(i + 1, tuesdayIncidences));
            cellList.add(new Cell(i + 2, wednesdayIncidences));
            cellList.add(new Cell(i + 3, thursdayIncidences));
            cellList.add(new Cell(i + 4, fridayIncidences));
            cellList.add(new Cell(i + 5, saturdayIncidences));
            cellList.add(new Cell(i + 6, sundayIncidences));
            i++;
        }

        return cellList;
    }
}
