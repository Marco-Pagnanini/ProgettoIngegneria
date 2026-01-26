package org.example.Application.Service;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
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
            throw new IllegalArgumentException("Validazione fallita");
        }
        User created = unitOfWork.userRepository().create(toAdd);
        unitOfWork.saveChanges();
        return created;
    }

    @Override
    public UserResponse visualizzaProfilo(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        if (user == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        return UserMapper.toResponse(user);
    }

    @Override
    public List<Invito> consultaInviti(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        if (user == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        return user.getInviti();
    }

    @Override
    public User accesso(UserLoginRequest request) {
        User user = unitOfWork.userRepository().findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return unitOfWork.userRepository().getAll();
    }

    @Override
    public User deleteById(Long id) {
        User deleted = unitOfWork.userRepository().delete(id);
        unitOfWork.saveChanges();
        return deleted;
    }

}
