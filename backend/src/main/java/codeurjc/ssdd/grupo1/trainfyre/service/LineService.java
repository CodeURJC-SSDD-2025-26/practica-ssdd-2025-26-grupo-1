package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;

import java.util.List;

public interface LineService {
    List<LineDTO> getAllLines();

    LineDTO getLineByName(String name);

    void updateLine(String oldName,String newName, String newDescription);

    void deleteLine(String name);

    void addLine(String name, String description);
}
