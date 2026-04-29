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
    void createUser(UserRegistrationtDTO userRegistrationtDTO);
    void createUser(UserDTO userDTO);

    //READ
    UserInfoDTO findUser(UserDetails userDetails);

    UserDTO giveUser(UserDetails userDetails);

    List<UserInfoDTO> findAllUsers();

    //UPDATE
    UserDetails updateUser(UserDetails currentUser, MultipartFile updatedImage, UserRegistrationtDTO newUserData);

    void updateUser(String oldUserName,MultipartFile updatedImage, UserInfoDTO newUserData);

    //DELETE
    void deleteUser(UserInfoDTO userInfoDTO);

    //NOTIFY
    void notifyIncidenceToAffectedUsers(IncidenceDTO incidenceDTO);
}
