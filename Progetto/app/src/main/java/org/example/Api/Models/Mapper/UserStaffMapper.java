package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Request.UserStaffRequest;
import org.example.Api.Models.Response.UserStaffResponse;
import org.example.Core.models.User;
import org.example.Core.models.UserStaff;

import java.time.LocalDateTime;

public class UserStaffMapper {
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
        userStaff.setSegnalazioniRicevute(userStaff.getSegnalazioniRicevute());
        return userStaff;
    }
}
