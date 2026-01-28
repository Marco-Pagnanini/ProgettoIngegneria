package org.example.Application.Service;

import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.UnauthorizedException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Invito;
import org.example.Core.models.User;
import org.example.utils.Facade.JwtFacade;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    private final IUnitOfWork unitOfWork;
    private final Validator<User> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtFacade jwtService;

    public UserService(IUnitOfWork unitOfWork, Validator<User> validator,
                       PasswordEncoder passwordEncoder, JwtFacade jwtService) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User registrazioneUtente(UserRequest user) {
        User toAdd = UserMapper.toEntity(user);
        if (!validator.validate(toAdd)) {
            throw new ValidationException("Dati utente non validi");
        }
        // Hash della password prima di salvarla
        toAdd.setPassword(passwordEncoder.encode(toAdd.getPassword()));
        User created = unitOfWork.userRepository().create(toAdd);
        unitOfWork.saveChanges();
        return created;
    }

    @Override
    public UserResponse visualizzaProfilo(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        if (user == null) {
            throw new ResourceNotFoundException("Utente con id " + idUtente + " non trovato");
        }
        return UserMapper.toResponse(user);
    }

    @Override
    public List<Invito> consultaInviti(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        if (user == null) {
            throw new ResourceNotFoundException("Utente con id " + idUtente + " non trovato");
        }
        return user.getInviti();
    }

    @Override
    public TokenResponse accesso(UserLoginRequest request) {
        if(request == null || request.getEmail() == null || request.getPassword() == null)
            throw new ValidationException("Email e password sono obbligatorie");
        User user = unitOfWork.userRepository().findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Password o email errati");
        }

        // Crea UserDetails per generare il token
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRuolo().name()))
                );

        // Aggiungi claims extra (email, id, ruolo)
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("ruolo", user.getRuolo().name());

        String jwtToken = jwtService.generateToken(extraClaims, userDetails);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccess_token(jwtToken);
        tokenResponse.setToken_type("Bearer");
        tokenResponse.setExpires_in(String.valueOf(jwtService.getExpirationTime()));

        return tokenResponse;
    }

    @Override
    public List<User> findAll() {
        return unitOfWork.userRepository().getAll();
    }

    @Override
    public User deleteById(Long id) {
        User deleted = unitOfWork.userRepository().delete(id);
        if (deleted == null) {
            throw new ResourceNotFoundException("Utente con id " + id + " non trovato");
        }
        unitOfWork.saveChanges();
        return deleted;
    }

}
