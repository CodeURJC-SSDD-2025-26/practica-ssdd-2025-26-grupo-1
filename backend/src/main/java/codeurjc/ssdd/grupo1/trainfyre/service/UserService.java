package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    //CREATE
    UserInfoDTO createUser(UserRegistrationtDTO userRegistrationtDTO);

    //READ
    UserInfoDTO findUser(UserDetails userDetails);

    List<UserInfoDTO> findAllUsers();

    //UPDATE
    UserInfoDTO updateUser(UserRegistrationtDTO userRegistrationtDTO);

    //DELETE
    void deleteUser(UserDetails userDetails);
}
