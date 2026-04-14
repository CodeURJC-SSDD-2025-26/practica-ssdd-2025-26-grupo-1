package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.IncidenceDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.dto.UsersDTOs.UserRegistrationtDTO;
import codeurjc.ssdd.grupo1.trainfyre.mapper.UserMapper;
import codeurjc.ssdd.grupo1.trainfyre.service.EmailService;
import codeurjc.ssdd.grupo1.trainfyre.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticatorUserService authenticatorUserService;
    private EmailService emailService;

    @Transactional
    public void createUser(UserRegistrationtDTO userRegistrationtDTO) {
        Optional<AppUser> appUser = this.findUserByUsername(userRegistrationtDTO.username());
        appUser.ifPresentOrElse(
                user -> {
                    throw new IllegalArgumentException("Error al registrarse. El nombre de usuario " + userRegistrationtDTO.username() + " ya existe" );
                },
                () -> {
                    AppUser newUser = new AppUser();
                    newUser.setUsername(userRegistrationtDTO.username());
                    newUser.setEmail(userRegistrationtDTO.email());
                    newUser.setPassword(passwordEncoder.encode(userRegistrationtDTO.password()));
                    newUser.setRole(Role.REGISTERED);
                    repository.save(newUser);

                    this.notifyUserByEmail(new String[]{newUser.getEmail()}, "Te has registrado con el usuario, "+userRegistrationtDTO.username(), "Bienvenido a TrainFyre ⚡, ahora tendrás acceso a más servicios");
                });
    }

    @Transactional
    public void createUser(UserDTO userDTO){
        Optional<AppUser> appUser = this.findUserByUsername(userDTO.username());
        appUser.ifPresentOrElse(
                user -> {
                    throw new IllegalArgumentException("Error al registrarse. El nombre de usuario  " + userDTO.username() + " ya existe");
                },
                () -> {
                    AppUser newUser = new AppUser();
                    newUser.setUsername(userDTO.username());
                    newUser.setEmail(userDTO.email());
                    newUser.setPassword(passwordEncoder.encode(userDTO.password()));
                    newUser.setRole(userDTO.role());
                    repository.save(newUser);

                    this.notifyUserByEmail(new String[]{newUser.getEmail()}, "Un administrador a registrado una cuenta con este gmail a TrainFyre", "Este gmail es un mensaje generado automaticamente");
                });

    }

    public UserInfoDTO findUser(UserDetails userDetails) {
        if (userDetails == null){
            return null;
        }

        return this.findUserByUsername(userDetails.getUsername())
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
        AppUser appUser = this.findUserByUsername(currentUser.getUsername())
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
        AppUser appUser = this.findUserByUsername(oldUserName)
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
        AppUser appUser = this.findUserByUsername(userInfoDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Error, usuario no encontrado: " + userInfoDTO.username()));

        repository.delete(appUser);
    }

    @Override
    public void notifyIncidenceToAffectedUsers(IncidenceDTO incidenceDTO) {
        //TODO
        log.info("Notificando a los usuarios afectados por la incidencia: {}", incidenceDTO.description());
        try {
            Thread.sleep(20000); // Simular tiempo de procesamiento
            log.info("Notificación enviada a los usuarios afectados por la incidencia: {}", incidenceDTO.description());
        } catch (InterruptedException e) {
            log.error("Error al notificar a los usuarios afectados por la incidencia: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private Optional<AppUser> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    private void notifyUserByEmail(String[] to, String subject, String body) {
        SecurityContext context = SecurityContextHolder.getContext();
        CompletableFuture.runAsync(() -> {
            SecurityContextHolder.setContext(context); // propagar contexto
            emailService.sendEmail(to, subject, body);
        });
    }
}