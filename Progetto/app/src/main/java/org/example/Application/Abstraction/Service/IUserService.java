package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Core.models.Invito;
import org.example.Core.models.User;

import java.util.List;

public interface IUserService {
    User registrazioneUtente(UserRequest user);

    UserResponse visualizzaProfilo(Long idUtente);

    List<Invito> consultaInviti(Long idUtente);

    User accesso(UserLoginRequest request);

}
