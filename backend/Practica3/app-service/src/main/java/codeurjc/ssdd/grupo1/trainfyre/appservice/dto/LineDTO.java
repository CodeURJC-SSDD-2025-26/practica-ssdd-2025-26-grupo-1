package codeurjc.ssdd.grupo1.trainfyre.appservice.dto;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Station;

import java.util.List;

public record LineDTO (

        Long id,
        String name,
        String description,
        String color,
        List<Station> stations
) {}

