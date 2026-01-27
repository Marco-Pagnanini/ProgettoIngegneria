package org.example.Api.Models.Mapper;

import org.example.Api.Models.Response.InvitoResponse;
import org.example.Core.models.Invito;

public class InvitoMapper {

    public static InvitoResponse toResponse(Invito invito) {
        InvitoResponse invitoResponse = new InvitoResponse();
        invitoResponse.setStatoInvito(invito.getStato());
        invitoResponse.setPerUtente(invito.getPerUtente().getNome());
        invitoResponse.setNomeTeam(invito.getDalTeam().getNome());
        return invitoResponse;
    }

}
