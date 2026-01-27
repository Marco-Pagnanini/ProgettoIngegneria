package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.Application.Abstraction.Repository.IUserRepository;
import org.example.Application.Abstraction.Repository.IUserStaffRepository;
import org.example.Core.models.User;
import org.example.Core.models.UserStaff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUserRepository userRepository;
    private final IUserStaffRepository userStaffRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Prima cerca tra gli User
            Optional<User> user = userRepository.findByEmail(username);
            if (user.isPresent()) {
                User u = user.get();
                return new org.springframework.security.core.userdetails.User(
                        u.getEmail(),
                        u.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + u.getRuolo().name()))
                );
            }

            // Poi cerca tra gli UserStaff
            Optional<UserStaff> userStaff = userStaffRepository.findByEmail(username);
            if (userStaff.isPresent()) {
                UserStaff us = userStaff.get();
                return new org.springframework.security.core.userdetails.User(
                        us.getEmail(),
                        us.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + us.getRuolo().name()))
                );
            }

            throw new UsernameNotFoundException("Utente non trovato con email: " + username);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
