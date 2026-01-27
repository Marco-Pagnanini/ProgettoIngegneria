package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidator implements Validator<User> {
    private IUnitOfWork unitOfWork;
    public UserValidator(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public boolean validate(User entity) {
        //CONTROLLI STANDARD
        if(entity == null) return false;
        if(entity.getEmail().isEmpty() || entity.getPassword().isEmpty()) return false;
        if(!entity.getEmail().contains("@")) return false;
        if(entity.getPassword().length() < 6 || entity.getPassword().length() > 16) return false;
        if(entity.getCellulare().length() != 10) return false;
        if(entity.getNome() == null || entity.getNome().isEmpty()) return false;
        if(entity.getCognome() == null || entity.getCognome().isEmpty()) return false;


        //CONTROLLO EMAIL UNIQUE
        List<User> users = unitOfWork.userRepository().getAll();
        for (User user : users) {
            if (user.getEmail().equals(entity.getEmail())) {
                return false;
            }
        }

        return true;
    }
}
