package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserRegistrationtDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AppUser userDTOToAppUser(UserDTO userDTO);

    AppUser userRegistrationDTOtoAppUser(UserRegistrationtDTO userRegistrationtDTO);

    UserDTO userToDTO(AppUser user);

    UserInfoDTO userToUserInfoDTO(AppUser user);
}
