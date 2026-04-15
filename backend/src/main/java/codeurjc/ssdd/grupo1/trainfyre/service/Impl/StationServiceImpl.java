package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.repository.StationRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.StationDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.StationRestMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {
    private StationRepository repository;
    private StationRestMapper stationRestMapper;

    @Override
    public List<StationDTO> getAllStations() {
        return this.repository.findAll().stream()
                .map(this.stationRestMapper::toStationDto)
                .toList();
    }
}
