package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //CREATE
    void createUser(UserRegistrationtDTO userRegistrationtDTO);
    void createUser(UserDTO userDTO);

    //READ
    UserInfoDTO findUser(UserDetails userDetails);

    List<UserInfoDTO> findAllUsers();

    //UPDATE
    UserDetails updateUser(UserDetails currentUser, MultipartFile updatedImage, UserRegistrationtDTO newUserData);

    void updateUser(String oldUserName,MultipartFile updatedImage, UserInfoDTO newUserData);

    //DELETE
    void deleteUser(UserInfoDTO userInfoDTO);
}
