package org.example.Application.Service;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.Invito;
import org.example.Core.models.User;
import org.example.Core.models.UserStaff;
import org.example.Infrastructure.Repository.UserStaffRepository;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.List;

public class UserStaffService implements IUserStaffService {

    private IUnitOfWork unitOfWork;

    public UserStaffService(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }


    @Override
    public UserStaff visitaProfilo(Long id) {
        return unitOfWork.userStaffRepository().getById(id);
    }

    @Override
    public TokenResponse accesso(UserLoginRequest request) {
        UserStaff userStaff = unitOfWork.userStaffRepository().findByEmail(request.getEmail());
        if (userStaff != null && userStaff.getPassword().equals(request.getPassword())) {
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setAccess_token("ACCESS");
            tokenResponse.setToken_type("JWT");
            tokenResponse.setExpires_in("...");
            return tokenResponse;
        }
        return null;
    }
}
