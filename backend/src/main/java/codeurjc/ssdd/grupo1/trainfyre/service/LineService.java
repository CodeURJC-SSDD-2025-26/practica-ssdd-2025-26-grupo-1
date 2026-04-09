package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.LineDTO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LineService {
    List<LineDTO> getAllLines();
}
