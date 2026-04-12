package codeurjc.ssdd.grupo1.trainfyre.dto;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import java.time.LocalDate;
import java.time.LocalTime;

public record AlertDTO(

        String id,

        String title,

        Line line,

        String startDate,
        
        String endDate,

        String startHour,

        String endHour,

        AppUser user
){}