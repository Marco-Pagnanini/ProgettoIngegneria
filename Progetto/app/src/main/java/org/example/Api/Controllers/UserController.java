package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Core.models.Invito;
import org.example.Core.models.User;

import java.util.List;

public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public User registerUser(UserRequest userRequest) {
        return userService.registrazioneUtente(userRequest);
    }

    public UserResponse visualizzaProfilo(Long idUtente){return  userService.visualizzaProfilo(idUtente);}


    public List<Invito> consultaInviti(Long idUtente){
        return userService.consultaInviti(idUtente);
    }

    public User accesso(UserLoginRequest request){
        return userService.accesso(request);
    }
}
