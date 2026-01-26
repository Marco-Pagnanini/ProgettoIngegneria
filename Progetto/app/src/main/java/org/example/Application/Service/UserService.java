package org.example.Application.Service;

import lombok.RequiredArgsConstructor;
import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Infrastructure.Abstraction.UserRepositoryJpa;
import org.example.Core.models.Invito;
import org.example.Core.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepositoryJpa userRepository;

    @Override
    public User registrazioneUtente(UserRequest user) {
        User toAdd = UserMapper.toEntity(user);
        return userRepository.save(toAdd);
    }

    @Override
    public UserResponse visualizzaProfilo(Long idUtente) {
        User user = userRepository.findById(idUtente)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        return UserMapper.toResponse(user);
    }

    @Override
    public List<Invito> consultaInviti(Long idUtente) {
        User user = userRepository.findById(idUtente)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        return user.getInviti();
    }

    @Override
    public User accesso(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        userRepository.deleteById(id);
        return user;
    }

}
