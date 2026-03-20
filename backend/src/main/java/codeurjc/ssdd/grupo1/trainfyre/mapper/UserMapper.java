package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AppUser userDTOToAppUser(UserDTO userDTO);

    AppUser userRegistrationDTOtoAppUser(UserRegistrationtDTO userRegistrationtDTO);

    UserDTO userToDTO(AppUser user);
}
