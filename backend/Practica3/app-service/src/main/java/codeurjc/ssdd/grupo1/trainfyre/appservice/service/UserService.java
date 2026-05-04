package codeurjc.ssdd.grupo1.trainfyre.appservice.service;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //CREATE
    UserInfoDTO registerUser(UserRegistrationtDTO userRegistrationtDTO);
    UserInfoDTO createUser(UserDTO userDTO);

    //READ
    UserInfoDTO findUser(UserDetails userDetails);

    UserInfoDTO findUser(Long id);

    UserDTO giveUser(UserDetails userDetails);

    List<UserInfoDTO> findAllUsers();

    //UPDATE
    UserDetails updateUser(UserDetails currentUser, MultipartFile updatedImage, UserRegistrationtDTO newUserData);

    UserInfoDTO updateUser(Long id, UserDTO newUser);

    void updateUser(String oldUserName,MultipartFile updatedImage, UserInfoDTO newUserData);

    //DELETE
    void deleteUser(UserInfoDTO userInfoDTO);
    UserInfoDTO deleteUser(Long id);

    //NOTIFY
    void notifyIncidenceToAffectedUsers(IncidenceDTO incidenceDTO);
}
