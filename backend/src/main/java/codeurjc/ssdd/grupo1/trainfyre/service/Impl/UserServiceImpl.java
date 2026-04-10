package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticatorUserService authenticatorUserService;

    @Transactional
    public UserInfoDTO createUser(UserRegistrationtDTO userRegistrationtDTO) {

        AppUser appUser = new AppUser();
        appUser.setUsername(userRegistrationtDTO.username());
        appUser.setEmail(userRegistrationtDTO.email());
        appUser.setPassword(passwordEncoder.encode(userRegistrationtDTO.password()));
        appUser.setRole(Role.REGISTERED);
        repository.save(appUser);

        return repository.findByUsername(userRegistrationtDTO.username())
                .map(userMapper::userToUserInfoDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al registrarse: " + userRegistrationtDTO.username()));
    }

    @Transactional
    public UserInfoDTO findUser(UserDetails userDetails) {

        if (userDetails == null){
            return null;
        }

        return repository.findByUsername(userDetails.getUsername())
                .map(userMapper::userToUserInfoDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Error al obtener el usuario: " + userDetails.getUsername()));
    }

    //todo Todavía no esta implementado, es para evitar errores de compilación
    @Override
    public List<UserInfoDTO> findAllUsers() {
        return this.repository.findAll().stream()
                .map(this.userMapper::userToUserInfoDTO)
                .toList();
    }

    @Override
    @Transactional
    public UserDetails updateUser(UserDetails currentUser, UserRegistrationtDTO newUserData) {

        AppUser appUser = repository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + currentUser.getUsername()));


        if (newUserData.username() != null && !newUserData.username().isBlank()) {
            appUser.setUsername(newUserData.username());
        }
        if (newUserData.email() != null && !newUserData.email().isBlank()) {
            appUser.setEmail(newUserData.email());
        }
        if (newUserData.password() != null && !newUserData.password().isBlank()) {
            appUser.setPassword(passwordEncoder.encode(newUserData.password()));
        }
        if(newUserData.role() != null){
            appUser.setRole(newUserData.role());
        }
        if(newUserData.image() != null){
            appUser.setImage(newUserData.image());
        }

        repository.save(appUser);
        return authenticatorUserService.loadUserByUsername(appUser.getUsername());
    }

    @Override
    public void deleteUser(UserDetails userDetails) {
        //TODO
    }
}
