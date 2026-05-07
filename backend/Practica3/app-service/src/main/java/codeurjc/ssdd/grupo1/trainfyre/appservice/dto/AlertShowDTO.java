package codeurjc.ssdd.grupo1.trainfyre.appservice.dto;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;

public record AlertShowDTO(

        Long id,

        String line,

        String startDate,
        
        String endDate,

        String startHour,

        String endHour
){}