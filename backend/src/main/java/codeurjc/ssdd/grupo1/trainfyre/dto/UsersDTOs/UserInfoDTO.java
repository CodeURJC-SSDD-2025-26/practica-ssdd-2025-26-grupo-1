package codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;

import java.util.List;

public record UserInfoDTO(

    String username,
    String email,
    Role role,
    byte[] image,
    List<Alert> alerts

) {}
