package codeurjc.ssdd.grupo1.trainfyre.dto;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import java.time.LocalDate;
import java.time.LocalTime;

public record AlertRegistrationDTO(

        //We don't have LineDTO yet.
        Line line, 

        LocalDate startDate,
        
        LocalDate endDate,

        LocalTime startHour,

        LocalTime endHour
){}