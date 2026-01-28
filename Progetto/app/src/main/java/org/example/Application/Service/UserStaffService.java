package org.example.Application.Service;

import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.UnauthorizedException;
import org.example.Api.Models.Mapper.UserStaffMapper;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Api.Models.Request.UserStaffRequest;
import org.example.Api.Models.Response.TokenResponse;
import org.example.Application.Abstraction.Service.IUserStaffService;
import org.example.Core.models.UserStaff;
import org.example.utils.Facade.JwtFacade;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserStaffService implements IUserStaffService {

    private final IUnitOfWork unitOfWork;
    private final PasswordEncoder passwordEncoder;
    private final JwtFacade jwtService;

    public UserStaffService(IUnitOfWork unitOfWork, PasswordEncoder passwordEncoder, JwtFacade jwtService) {
        this.unitOfWork = unitOfWork;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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
        UserStaff userStaff = unitOfWork.userStaffRepository().findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide"));

        if (!passwordEncoder.matches(request.getPassword(), userStaff.getPassword())) {
            throw new UnauthorizedException("Credenziali non valide");
        }

        // Crea UserDetails per generare il token
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        userStaff.getEmail(),
                        userStaff.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userStaff.getRuolo().name()))
                );

        // Aggiungi claims extra (email, id, ruolo)
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", userStaff.getId());
        extraClaims.put("email", userStaff.getEmail());
        extraClaims.put("ruolo", userStaff.getRuolo().name());

        String jwtToken = jwtService.generateToken(extraClaims, userDetails);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccess_token(jwtToken);
        tokenResponse.setToken_type("Bearer");
        tokenResponse.setExpires_in(String.valueOf(jwtService.getExpirationTime()));

        return tokenResponse;
    }

    @Override
    public UserStaff add(UserStaffRequest userStaff) {
        UserStaff user = UserStaffMapper.toEntity(userStaff);
        // Hash della password prima di salvarla
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return unitOfWork.userStaffRepository().create(user);
    }


}
