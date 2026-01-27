package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Request.UserStaffRequest;
import org.example.Api.Models.Response.UserStaffResponse;
import org.example.Core.models.User;
import org.example.Core.models.UserStaff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserStaffMapper {

    public static UserStaff toEntity(UserStaffRequest userStaff) {
        UserStaff user = new UserStaff();
        user.setEmail(userStaff.getEmail());
        user.setCellulare(userStaff.getCellulare());
        user.setDataCreazione(LocalDateTime.now());
        user.setDataNascita(userStaff.getDataDiNascita());
        user.setRuolo(userStaff.getRuolo());
        user.setNome(userStaff.getNome());
        user.setPassword(userStaff.getPassword());
        user.setCognome(userStaff.getCognome());
        user.setHackathonOrganizzati(new ArrayList<>());
        user.setHackathonSupportati(new ArrayList<>());
        user.setHackathonValutati(new ArrayList<>());

        return user;

    }

    public static UserStaffResponse toResponse(UserStaff request) {
        UserStaffResponse userStaff = new UserStaffResponse();
        userStaff.setNome(request.getNome());
        userStaff.setCognome(request.getCognome());
        userStaff.setEmail(request.getEmail());
        userStaff.setCellulare(request.getCellulare());
        userStaff.setDataNascita(request.getDataNascita());
        userStaff.setDataCreazione(LocalDateTime.now());

        userStaff.setRuolo(userStaff.getRuolo());

        userStaff.setHackathonOrganizzati(userStaff.getHackathonOrganizzati());
        userStaff.setHackathonValutati(userStaff.getHackathonValutati());
        userStaff.setHackathonSupportati(userStaff.getHackathonSupportati());
        return userStaff;
    }
}
