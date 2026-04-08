package codeurjc.ssdd.grupo1.trainfyre.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.Mapper;
import org.modelmapper.ModelMapper;

public class UserToUserInfoDto implements Mapper<AppUser, UserInfoDTO> {
    @Override
    public UserInfoDTO map(AppUser input) {
        return new ModelMapper().map(input, UserInfoDTO.class);
    }
}
