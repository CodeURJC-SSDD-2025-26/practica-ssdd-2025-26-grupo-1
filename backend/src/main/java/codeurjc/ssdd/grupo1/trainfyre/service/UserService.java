package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //CREATE
    UserInfoDTO createUser(UserRegistrationtDTO userRegistrationtDTO);

    //READ
    UserInfoDTO findUser(UserDetails userDetails);

    List<UserInfoDTO> findAllUsers();

    //UPDATE
    UserDetails updateUser(UserDetails currentUser, MultipartFile updatedImage, UserRegistrationtDTO newUserData);

    void updateUser(String oldUserName,MultipartFile updatedImage, UserInfoDTO newUserData);

    //DELETE
    void deleteUser(UserInfoDTO userInfoDTO);
}
