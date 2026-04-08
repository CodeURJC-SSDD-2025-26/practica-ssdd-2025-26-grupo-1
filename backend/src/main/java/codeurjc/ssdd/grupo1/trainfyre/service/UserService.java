package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDTO registerUser(UserRegistrationtDTO userRegistrationtDTO);

    UserDTO findUser(UserDetails userDetails);
}
