package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.LineRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.LineMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.LineService;
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
    @Transactional
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

    //UPDATE
    @Override
    @Transactional
    public void updateLine(String oldName,String newName, String newDescription) {
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
        
        repository.save(line);
    }

    @Override
    @Transactional
    public void addLine(String newName, String newDescription) {
        Line line = new Line();

        if (newName != null && !newName.isBlank()) {
            line.setName(newName);
        }
        if (newDescription != null && !newDescription.isBlank()) {
            line.setDescription(newDescription);
        }

        repository.save(line);
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
