package org.example.Application.Service;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserRequest;
import org.example.Application.Abstraction.Repository.IUserRepository;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Validator.Validator;
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
    public User registerUser(UserRequest user) {

        //MAPPER
        User toAdd = UserMapper.toEntity(user);

        if(!validator.validate(toAdd)) return null;



        unitOfWork.userRepository().create(toAdd);
        unitOfWork.saveChanges();
        return toAdd;
    }
}
