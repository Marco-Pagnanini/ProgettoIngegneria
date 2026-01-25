package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Core.models.UserStaff;

public interface IUserStaffService {
    UserStaff visitaProfilo(Long id);
    UserStaff accesso(UserLoginRequest request);
}
