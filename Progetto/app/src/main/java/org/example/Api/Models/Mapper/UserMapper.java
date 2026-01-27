package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.UserRequest;
import org.example.Api.Models.Response.UserResponse;
import org.example.Core.models.User;
import org.example.Core.enums.RuoloUser;

import java.time.LocalDateTime;

/**
 * Mapper per la conversione tra User entity e User DTOs
 *
 * @author Marco Pagnanini
 */
public class UserMapper {

    /**
     * Converte una UserRequest in un'entità User del dominio
     *
     * @param request la richiesta contenente i dati dell'utente
     * @return l'entità User popolata
     */
    public static User toEntity(UserRequest request) {
        User user = new User();
        user.setNome(request.getNome());
        user.setCognome(request.getCognome());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCellulare(request.getCellulare());
        user.setDataNascita(request.getDataDiNascita());
        user.setDataCreazione(LocalDateTime.now());

        return user;
    }

    /**
     * Converte un'entità User in una UserResponse
     *
     * @param user l'entità User del dominio
     * @return il DTO di risposta contenente i dati dell'utente
     */
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setNome(user.getNome());
        response.setCognome(user.getCognome());
        response.setEmail(user.getEmail());
        response.setCellulare(user.getCellulare());
        response.setDataNascita(user.getDataNascita());
        response.setDataCreazione(user.getDataCreazione());
        response.setRuolo(user.getRuolo());

        // Mapping delle relazioni se presenti
        if (user.getTeam() != null) {
            response.setTeamId(user.getTeam().getId());
            response.setTeamNome(user.getTeam().getNome());
        }

        return response;
    }

    /**
     * Aggiorna un'entità User esistente con i dati dalla UserRequest
     * Utile per operazioni di update
     *
     * @param user l'entità User esistente da aggiornare
     * @param request la richiesta contenente i nuovi dati
     */
    public static void updateEntityFromRequest(User user, UserRequest request) {
        if (request.getNome() != null) {
            user.setNome(request.getNome());
        }
        if (request.getCognome() != null) {
            user.setCognome(request.getCognome());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getCellulare() != null) {
            user.setCellulare(request.getCellulare());
        }
        if (request.getDataDiNascita() != null) {
            user.setDataNascita(request.getDataDiNascita());
        }
    }
}
