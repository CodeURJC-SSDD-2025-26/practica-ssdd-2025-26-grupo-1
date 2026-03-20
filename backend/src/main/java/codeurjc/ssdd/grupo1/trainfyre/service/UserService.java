package codeurjc.ssdd.grupo1.trainfyre.service;

import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;

public interface UserService {

    UserDTO registerUser(UserRegistrationtDTO userRegistrationtDTO);
}
