package codeurjc.ssdd.grupo1.trainfyre.mapper;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-08T12:21:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public AppUser userDTOToAppUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        AppUser.AppUserBuilder appUser = AppUser.builder();

        appUser.id( userDTO.id() );
        appUser.username( userDTO.username() );
        appUser.password( userDTO.password() );
        appUser.role( userDTO.role() );
        appUser.email( userDTO.email() );

        return appUser.build();
    }

    @Override
    public AppUser userRegistrationDTOtoAppUser(UserRegistrationtDTO userRegistrationtDTO) {
        if ( userRegistrationtDTO == null ) {
            return null;
        }

        AppUser.AppUserBuilder appUser = AppUser.builder();

        appUser.username( userRegistrationtDTO.username() );
        appUser.password( userRegistrationtDTO.password() );
        appUser.role( userRegistrationtDTO.role() );
        appUser.email( userRegistrationtDTO.email() );

        return appUser.build();
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

    @Override
    public UserInfoDTO userToUserInfoDTO(AppUser user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String email = null;
        Role role = null;
        byte[] image = null;
        List<Alert> alerts = null;

        username = user.getUsername();
        email = user.getEmail();
        role = user.getRole();
        byte[] image1 = user.getImage();
        if ( image1 != null ) {
            image = Arrays.copyOf( image1, image1.length );
        }
        List<Alert> list = user.getAlerts();
        if ( list != null ) {
            alerts = new ArrayList<Alert>( list );
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO( username, email, role, image, alerts );

        return userInfoDTO;
    }
}
