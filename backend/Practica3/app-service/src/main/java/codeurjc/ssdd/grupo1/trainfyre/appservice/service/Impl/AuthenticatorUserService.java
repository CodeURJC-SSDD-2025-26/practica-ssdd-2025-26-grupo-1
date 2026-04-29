package codeurjc.ssdd.grupo1.trainfyre.appservice.service.Impl;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository.UserRepository;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticatorUserService implements UserDetailsService {

    private UserRepository repository;
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO user = repository.findByUsername(username)
                .map(userMapper::userToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("No encontrado: " + username));


        return org.springframework.security.core.userdetails.User
                .withUsername(user.username())
                .password(user.password())
                .roles(user.role().name())
                .build();
    }
}
