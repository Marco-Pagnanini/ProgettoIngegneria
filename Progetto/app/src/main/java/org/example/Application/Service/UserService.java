package org.example.Application.Service;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;

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
}
