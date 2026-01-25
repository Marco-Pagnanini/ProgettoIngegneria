package org.example.Api.Controllers;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Application.Abstraction.Repository.IUserStaffRepository;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.UserStaff;

public class UserStaffController {
    private final IUserStaffService userService;

    public UserStaffController(IUserStaffService userService) {
        this.userService = userService;
    }

    public UserStaff accesso(UserLoginRequest userLoginRequest) {
        return userService.accesso(userLoginRequest);
    }

    public UserStaff visitaProfilo(Long id){
        return userService.visitaProfilo(id);
    }


}
