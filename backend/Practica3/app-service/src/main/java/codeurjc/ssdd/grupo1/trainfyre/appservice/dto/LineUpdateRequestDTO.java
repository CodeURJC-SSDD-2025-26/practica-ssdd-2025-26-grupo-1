package codeurjc.ssdd.grupo1.trainfyre.appservice.dto;

public record LineUpdateRequestDTO (
        String oldName,
        String newName,
        String description,
        String color
) {}