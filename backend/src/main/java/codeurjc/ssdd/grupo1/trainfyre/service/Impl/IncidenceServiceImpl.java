package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.IncidenceRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.*;
import codeurjc.ssdd.grupo1.trainfyre.mapper.IncidenceMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.IncidenceService;
import lombok.AllArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Random;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IncidenceServiceImpl implements IncidenceService {
    private IncidenceRepository incidenceRepository;
    private IncidenceRepository lineRepository;
    private IncidenceMapper incidenceMapper;
    private ObjectMapper jsonMapper;

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

    public String generatePieChartJSON() {
        // TODO: placeholder
        List<PieChartInfo> data = List.of(
            new PieChartInfo("Línea 1", 10),
            new PieChartInfo("Línea 2", 10),
            new PieChartInfo("Línea 3", 10),
            new PieChartInfo("Línea 4", 10),
            new PieChartInfo("Línea 5", 10),
            new PieChartInfo("Línea 6", 10),
            new PieChartInfo("Línea 7", 10),
            new PieChartInfo("Línea 8", 30)
        );

        return jsonMapper.writeValueAsString(data);
    }

    public String generateHeatmapJSON() {
        // TODO: placeholder
        HeatmapInfo heatmap = new HeatmapInfo();

        heatmap.setTitle("INCIDENCIAS SEMANALES");
        heatmap.setRows(10);
        heatmap.setColumns(7);

        heatmap.setRowLabels(List.of(
            "C-1","C-2","C-3","C-4","C-5",
            "C-7","C-8","C-9","C-10","CIVIS"
        ));

        heatmap.setRowColors(List.of(
            "rgb(50, 214, 255)", "green", "purple", "blue", "rgb(255, 230, 0)",
            "red", "grey", "brown", "greenyellow", "rgb(255, 111, 135)"
        ));

        heatmap.setColumnLabels(List.of(
            "Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"
        ));

        heatmap.setCells(generateHeatmapCells(10, 7));

        return jsonMapper.writeValueAsString(heatmap);
    }

    private List<Cell> generateHeatmapCells(int rows, int columns) {
        List<Cell> cellList = new ArrayList<>();
        int numberOfCells = rows * columns;

        Random random = new Random(); // TODO: placeholder

        for (int i = 0; i < numberOfCells; i++) {
            cellList.add(new Cell(i, random.nextInt(20)));
        }

        return cellList;
    }
}
