package codeurjc.ssdd.grupo1.trainfyre.security;

import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;                                            
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {                  


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/static/js/**", "/img/**",
                                "/", "/logout", "/h2-console/**", "/incidences",
                                "/login", "/index/**", "/successful_logout",
                                "/stations", "/register", "/error", "/js/**")
                                .permitAll()
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/registered/**").hasAnyRole(Role.ADMIN.name(), Role.REGISTERED.name())
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/successful_logout"))
                //Para poder cargar la consola h2
                .headers(headers -> headers.frameOptions(f -> f.sameOrigin()))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/h2-console/**"));

        return http.build();
    }
}