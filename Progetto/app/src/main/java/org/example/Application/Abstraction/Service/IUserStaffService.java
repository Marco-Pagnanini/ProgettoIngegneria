package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Core.models.UserStaff;

public interface IUserStaffService {
    UserStaff visitaProfilo(Long id);
    TokenResponse accesso(UserLoginRequest request);
}
