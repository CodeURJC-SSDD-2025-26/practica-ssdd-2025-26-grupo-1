package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser implements Mapper<UserDTO, AppUser> {
    @Override
    public AppUser map(UserDTO input) {
        return new ModelMapper().map(input,AppUser.class);
    }
}
