package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-20T18:55:08+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public AppUser userDTOToAppUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( userDTO.id() );
        appUser.setUsername( userDTO.username() );
        appUser.setPassword( userDTO.password() );
        appUser.setRole( userDTO.role() );
        appUser.setEmail( userDTO.email() );

        return appUser;
    }

    @Override
    public AppUser userRegistrationDTOtoAppUser(UserRegistrationtDTO userRegistrationtDTO) {
        if ( userRegistrationtDTO == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setUsername( userRegistrationtDTO.username() );
        appUser.setPassword( userRegistrationtDTO.password() );
        appUser.setRole( userRegistrationtDTO.role() );
        appUser.setEmail( userRegistrationtDTO.email() );

        return appUser;
    }

    @Override
    public UserDTO userToDTO(AppUser user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String password = null;
        String email = null;
        Role role = null;

        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        role = user.getRole();

        UserDTO userDTO = new UserDTO( id, username, password, email, role );

        return userDTO;
    }
}
