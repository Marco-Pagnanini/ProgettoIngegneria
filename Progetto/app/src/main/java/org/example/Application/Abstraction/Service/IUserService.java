package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Core.models.User;

public interface IUserService {
    User registrazioneUtente(UserRequest user);

    UserResponse visualizzaProfilo(Long idUtente);

}
