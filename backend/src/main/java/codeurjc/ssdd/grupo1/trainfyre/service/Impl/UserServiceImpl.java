package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO registerUser(UserRegistrationtDTO userRegistrationtDTO) {

        AppUser appUser = new AppUser();
        appUser.setUsername(userRegistrationtDTO.username());
        appUser.setEmail(userRegistrationtDTO.email());
        appUser.setPassword(passwordEncoder.encode(userRegistrationtDTO.password()));
        appUser.setRole(Role.REGISTERED);
        userRepository.save(appUser);

        return userRepository.findByUsername(userRegistrationtDTO.username())
                .map(userMapper::userToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al registrarse: " + userRegistrationtDTO.username()));
    }

    @Transactional
    public UserDTO findUser(UserDetails userDetails) {

        if (userDetails == null){
            return null;
        }

        return userRepository.findByUsername(userDetails.getUsername())
                .map(userMapper::userToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al obtener el usuario: " + userDetails.getUsername()));
    }
}
