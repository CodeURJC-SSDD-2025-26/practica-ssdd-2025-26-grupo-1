package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDtoToUser implements Mapper<UserRegistrationtDTO, AppUser> {
    @Override
    public AppUser map(UserRegistrationtDTO input) {
        return new ModelMapper().map(input, AppUser.class);
    }
}
