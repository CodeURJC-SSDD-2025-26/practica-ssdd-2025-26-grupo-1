package codeurjc.ssdd.grupo1.trainfyre.appservice.dto;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;

public record AlertRegistrationDTO(


        //We don't have LineDTO yet.
        String line, 

        String startDate,
        
        String endDate,

        String startHour,

        String endHour
){}