package codeurjc.ssdd.grupo1.trainfyre.dto;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;

public record AlertDTO(

        Long id,

        Line line,

        String startDate,
        
        String endDate,

        String startHour,

        String endHour,

        AppUser user
){}