package codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.Role;

import java.util.List;

public record UserInfoDTO(

    String username,
    String email,
    Role role,
    Long profileImageId,
    List<Alert> alerts

) {}
