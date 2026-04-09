package codeurjc.ssdd.grupo1.trainfyre.dto;
import java.awt.Color;
import java.util.List;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Station;

public record LineDTO (

        Long id,
        String name,
        String description,
        Color color,
        List<Station> stations
) {}

