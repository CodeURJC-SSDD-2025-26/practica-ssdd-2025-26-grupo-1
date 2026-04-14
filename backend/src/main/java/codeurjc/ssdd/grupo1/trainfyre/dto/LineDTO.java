package codeurjc.ssdd.grupo1.trainfyre.dto;
import java.util.List;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Station;

public record LineDTO (

        Long id,
        String name,
        String description,
        String color,
        List<Station> stations
) {}

