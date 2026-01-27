package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.UserStaffMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserStaffRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Api.Models.Response.UserStaffResponse;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.UserStaff;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/userStaff")
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
    public ResponseEntity<UserStaffResponse> visitaProfilo(@PathVariable Long id) {
        UserStaff user = userService.visitaProfilo(id);
        return ResponseEntity.ok(UserStaffMapper.toResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserStaff> postaProfilo(@RequestBody UserStaffRequest userStaffRequest) {
        return ResponseEntity.ok(userService.add(userStaffRequest));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ORGANIZZATORE', 'MENTORE', 'GIUDICE')")
    public ResponseEntity<Map<String, Object>> getCurrentStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();
        response.put("email", authentication.getName());
        response.put("authorities", authentication.getAuthorities());
        return ResponseEntity.ok(response);
    }
}
