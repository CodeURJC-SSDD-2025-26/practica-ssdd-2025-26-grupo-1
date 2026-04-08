package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserInfoDTO registerUser(UserRegistrationtDTO userRegistrationtDTO);

    UserInfoDTO findUser(UserDetails userDetails);
}
