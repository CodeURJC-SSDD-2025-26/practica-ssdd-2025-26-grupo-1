package codeurjc.ssdd.grupo1.trainfyre.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.repository.projection.UserAlertsCountView;
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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (!isAValidEmail(userRegistrationtDTO.email())){
            throw new IllegalArgumentException("Error al registrarse. el gmail no es un gmail válido " + userRegistrationtDTO.email());
        }

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

        if (!isAValidEmail(userDTO.email())){
            throw new IllegalArgumentException("Error al registrarse. el gmail no es un gmail válido " + userDTO.email());
        }

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
        log.info("Notificando a los usuarios afectados por la incidencia: {}", incidenceDTO.description());

        List<String> lineNames = incidenceDTO.affectedLines() == null
                ? List.of()
                : incidenceDTO.affectedLines().stream()
                  .map(Line::getName)
                  .filter(name -> name != null && !name.isBlank())
                  .distinct()
                  .toList();

        if (lineNames.isEmpty()) {
            log.warn("La incidencia {} no tiene líneas afectadas; no se enviarán notificaciones.", incidenceDTO.id());
            return;
        }
        String linesText = String.join(", ", lineNames);

        this.getUsersGroupedByAlerts(incidenceDTO, lineNames)
                    .forEach(user -> {
                        String subject = "Notificación de incidencia en líneas: " + linesText;
                        String body = "Estimado/a " + user.getUsername() + ",\n\n" +
                                "Le informamos que se ha producido una incidencia en las líneas: " + linesText +
                                " que coincide con sus alertas configuradas.\n" +
                                "Descripción de la incidencia: " + incidenceDTO.description() + "\n" +
                                "Fecha y hora de la incidencia: " + incidenceDTO.date() + "\n\n" +
                                "Le recomendamos revisar su configuración de alertas para estas líneas y tomar las precauciones necesarias.\n\n" +
                                "Atentamente,\n" +
                                "El equipo de TrainFyre";

                        this.notifyUserByEmail(new String[]{user.getEmail()}, subject, body);
                    });

            log.info("Notificación enviada a los usuarios afectados por la incidencia: {}", incidenceDTO.description());

    }

    private List<UserAlertsCountView> getUsersGroupedByAlerts(IncidenceDTO incidenceDTO, List<String> lineNames) {
        String incDate = incidenceDTO.date().toLocalDate().toString();
        String incHour = incidenceDTO.date().toLocalTime().withSecond(0).withNano(0).toString();
        if (incHour.length() > 5) incHour = incHour.substring(0, 5);

        return repository.findUsersWithMatchingAlertsGrouped(
                incidenceDTO.id(),
                lineNames,
                incDate,
                incHour
        );
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

    private static boolean isAValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}