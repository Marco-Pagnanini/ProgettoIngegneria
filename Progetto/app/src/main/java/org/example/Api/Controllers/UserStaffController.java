package org.example.Api.Controllers;

import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.UserStaff;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userStaff")
public class UserStaffController {
    private final IUserStaffService userService;

    public UserStaffController(IUserStaffService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> accesso(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null || userLoginRequest.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        TokenResponse response = userService.accesso(userLoginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStaff> visitaProfilo(@PathVariable Long id) {
        UserStaff user = userService.visitaProfilo(id);
        return ResponseEntity.ok(user);
    }
}
