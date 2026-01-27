package org.example.Application.Service;

import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.UnauthorizedException;
import org.example.Api.Models.Mapper.UserStaffMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserStaffRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.UserStaff;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

@Service
public class UserStaffService implements IUserStaffService {

    private final IUnitOfWork unitOfWork;

    public UserStaffService(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public UserStaff visitaProfilo(Long id) {
        UserStaff user = unitOfWork.userStaffRepository().getById(id);
        if(user == null) {
            throw new ResourceNotFoundException("Staff con id " + id + " non trovato");
        }
        unitOfWork.saveChanges();
        return user;
    }

    @Override
    public TokenResponse accesso(UserLoginRequest request) {
        UserStaff userStaff = unitOfWork.userStaffRepository().findByEmail(request.getEmail());
        if(userStaff == null) {
            throw new UnauthorizedException("Credenziali non valide");
        }
        if (userStaff.getPassword().equals(request.getPassword())) {
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setAccess_token("ACCESS");
            tokenResponse.setToken_type("JWT");
            tokenResponse.setExpires_in("...");
            unitOfWork.saveChanges();
            return tokenResponse;
        }
        throw new UnauthorizedException("Credenziali non valide");
    }

    @Override
    public UserStaff add(UserStaffRequest userStaff) {
        UserStaff  user = UserStaffMapper.toEntity(userStaff);

        return unitOfWork.userStaffRepository().create(user);
    }


}
