package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.EmailService;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticatorUserService authenticatorUserService;
    private EmailService emailService;

    @Transactional
    public UserInfoDTO createUser(UserRegistrationtDTO userRegistrationtDTO) {

        AppUser appUser = new AppUser();
        appUser.setUsername(userRegistrationtDTO.username());
        appUser.setEmail(userRegistrationtDTO.email());
        appUser.setPassword(passwordEncoder.encode(userRegistrationtDTO.password()));
        appUser.setRole(Role.REGISTERED);
        repository.save(appUser);

        emailService.sendEmail(appUser.getEmail(), "Te has registrado con el usuario, "+userRegistrationtDTO.username(), "Bienvenido a TrainFyre ⚡, ahora tendrás acceso a más servicios");

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

    @Override
    public List<UserInfoDTO> findAllUsers() {
        return this.repository.findAll().stream()
                .map(this.userMapper::userToUserInfoDTO)
                .toList();
    }

    @Override
    @Transactional
    public UserDetails updateUser(UserDetails currentUser, MultipartFile updatedImage, UserRegistrationtDTO newUserData) {

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
        if(updatedImage != null){
            byte[] imageData;
            try {
                imageData = updatedImage.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e + "error en la lectura del archivo");
            }
            appUser.setImage(imageData);
        }

        repository.save(appUser);
        return authenticatorUserService.loadUserByUsername(appUser.getUsername());
    }

    @Override
    @Transactional
    public void updateUser(String oldUserName, MultipartFile updatedImage, UserInfoDTO newUserData){

        AppUser appUser = repository.findByUsername(oldUserName)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + oldUserName));

        if (newUserData.username() != null && !newUserData.username().isBlank()) {
            appUser.setUsername(newUserData.username());
        }
        if (newUserData.email() != null && !newUserData.email().isBlank()) {
            appUser.setEmail(newUserData.email());
        }
        if(newUserData.role() != null){
            appUser.setRole(newUserData.role());
        }
        if(updatedImage != null && !updatedImage.isEmpty()){
            byte[] imageData;
            try {
                imageData = updatedImage.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e + "error en la lectura del archivo");
            }
            appUser.setImage(imageData);
        }

        repository.save(appUser);
    }

    @Override
    public void deleteUser(UserInfoDTO userInfoDTO) {

        AppUser userToDelete = repository.findByUsername(userInfoDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Error, usuario no encontrado: " + userInfoDTO.username()));

        repository.delete(userToDelete);
    }
}
