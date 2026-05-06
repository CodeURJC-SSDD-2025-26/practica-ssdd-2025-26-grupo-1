package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository.LineRepository;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.LineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class LineImpl implements LineService {
    @Autowired
    private LineRepository repository;

    @Autowired
    private LineMapper lineRestMapper;

    @Override
    public List<LineDTO> getAllLines() {
        return this.repository.findAll().stream()
                .map(this.lineRestMapper::toLineDto)
                .toList();
    }

    @Override
    public LineDTO getLineByName(String lineName) {
        if (lineName == null) {
            return null;
        }
        return repository.findByName(lineName)
                .map(this.lineRestMapper::toLineDto)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la línea con nombre: " + lineName
            ));
    }

    @Override
    public Line findLineByName(String lineName) {
        if (lineName == null) {
            return null;
        }
        return repository.findByName(lineName)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la línea con nombre: " + lineName
            ));
    }

    //UPDATE
    @Override
    @Transactional
    public LineDTO updateLine(String oldName, String newName, String newDescription, String newColor) {
        Line line = repository.findByName(oldName)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la línea con nombre: " + oldName
            ));

        if (newName != null && !newName.isBlank()) {
            line.setName(newName);
        }
        if (newDescription != null && !newDescription.isBlank()) {
            line.setDescription(newDescription);
        }
        if (newColor != null && !newColor.isBlank()) {
            line.setColor(newColor);
        }
        repository.save(line);
        return this.lineRestMapper.toLineDto(line);
    }

    @Override
    @Transactional
    public LineDTO addLine(String newName, String newDescription, String newColor) {
        Line line = new Line();

        if (newName != null && !newName.isBlank()) {
            line.setName(newName);
        }
        if (newDescription != null && !newDescription.isBlank()) {
            line.setDescription(newDescription);
        }
        if (newColor != null && !newColor.isBlank()) {
            line.setColor(newColor);
        }
        
        return this.lineRestMapper.toLineDto(repository.save(line));
    }

    //DELETE
    @Override
    @Transactional
    public void deleteLine(String name) {
        Line lineToDelete = repository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la línea con nombre: " + name
            ));

        repository.delete(lineToDelete);
    }
}
