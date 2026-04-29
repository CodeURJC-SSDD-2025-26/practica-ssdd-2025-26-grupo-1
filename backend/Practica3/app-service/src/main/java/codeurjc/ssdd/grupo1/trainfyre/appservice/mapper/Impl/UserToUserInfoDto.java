package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.Mapper;
import org.modelmapper.ModelMapper;

public class UserToUserInfoDto implements Mapper<AppUser, UserInfoDTO> {
    @Override
    public UserInfoDTO map(AppUser input) {
        return new ModelMapper().map(input, UserInfoDTO.class);
    }
}
