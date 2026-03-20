package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDtoToUser implements Mapper<UserRegistrationtDTO, AppUser> {
    @Override
    public AppUser map(UserRegistrationtDTO input) {
        return new ModelMapper().map(input, AppUser.class);
    }
}
