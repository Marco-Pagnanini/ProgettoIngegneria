package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.IUserService;
import org.example.Core.models.User;

public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public User registerUser(UserRequest userRequest) {
        return userService.registrazioneUtente(userRequest);
    }

    public UserResponse visualizzaProfilo(Long idUtente){return  userService.visualizzaProfilo(idUtente);}


}
