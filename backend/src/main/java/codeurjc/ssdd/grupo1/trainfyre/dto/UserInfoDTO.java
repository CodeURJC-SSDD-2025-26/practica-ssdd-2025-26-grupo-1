package codeurjc.ssdd.grupo1.trainfyre.dto;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;

import java.util.List;

public record UserInfoDTO(

    String username,
    String email,
    Role role,
    List<Alert> alerts

) {}
