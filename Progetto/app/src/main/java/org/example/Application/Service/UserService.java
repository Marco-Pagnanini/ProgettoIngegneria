package org.example.Application.Service;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Invito;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.List;

public class UserService implements IUserService {
    private IUnitOfWork unitOfWork;
    private Validator<User> validator;

    public UserService(IUnitOfWork unitOfWork, Validator<User> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public User registrazioneUtente(UserRequest user) {

        //MAPPER
        User toAdd = UserMapper.toEntity(user);

        if(!validator.validate(toAdd)) return null;

        unitOfWork.userRepository().create(toAdd);
        unitOfWork.saveChanges();
        return toAdd;
    }

    @Override
    public UserResponse visualizzaProfilo(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        if (user == null){
            throw new IllegalArgumentException("Utente non trovato");
        }
        return UserMapper.toResponse(user);
    }

    @Override
    public List<Invito> consultaInviti(Long idUtente) {
        User user = unitOfWork.userRepository().getById(idUtente);
        unitOfWork.saveChanges();
        return user.getInviti();
    }

    @Override
    public User accesso(UserLoginRequest request) {
        User user = unitOfWork.userRepository().findByEmail(request.getEmail());
        unitOfWork.saveChanges();
        return user;
    }
}
