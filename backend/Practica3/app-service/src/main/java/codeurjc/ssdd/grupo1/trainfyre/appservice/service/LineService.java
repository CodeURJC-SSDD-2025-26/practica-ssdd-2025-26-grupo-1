package codeurjc.ssdd.grupo1.trainfyre.appservice.service;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.LineDTO;

import java.util.List;

public interface LineService {
    List<LineDTO> getAllLines();

    LineDTO getLineByName(String name);

    Line findLineByName(String name);

    LineDTO updateLine(String oldName, String newName, String newDescription, String newColor);

    void deleteLine(String name);

    LineDTO addLine(String name, String description, String color);
}
