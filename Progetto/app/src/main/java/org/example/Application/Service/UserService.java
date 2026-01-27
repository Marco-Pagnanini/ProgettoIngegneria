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
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUnitOfWork unitOfWork;
    private final Validator<User> validator;

    public UserService(IUnitOfWork unitOfWork, Validator<User> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }

    @Override
    public User registrazioneUtente(UserRequest user) {
        User toAdd = UserMapper.toEntity(user);
        if (!validator.validate(toAdd)) {
            throw new ValidationException("Dati utente non validi");
        }
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
        User user = unitOfWork.userRepository().findByEmail(request.getEmail());
        if (user == null) {
            throw new UnauthorizedException("Credenziali non valide");
        }
        if (user.getPassword().equals(request.getPassword())) {
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setAccess_token("ACCESS");
            tokenResponse.setToken_type("JWT");
            tokenResponse.setExpires_in("...");
            unitOfWork.saveChanges();
            return tokenResponse;
        }
        throw new UnauthorizedException("Password o email errati");

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
