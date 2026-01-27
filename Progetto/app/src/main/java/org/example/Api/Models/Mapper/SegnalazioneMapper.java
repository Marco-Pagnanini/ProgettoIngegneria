package org.example.Api.Models.Mapper;

import org.example.Api.Models.Response.SegnalazioneResponse;
import org.example.Core.models.Segnalazione;

public class SegnalazioneMapper {

    public static SegnalazioneResponse toResponse(Segnalazione segnalazione) {
        SegnalazioneResponse res = new SegnalazioneResponse();

        res.setNome(segnalazione.getNome());
        res.setDescrizione(segnalazione.getDescrizione());

        if (segnalazione.getTeamSegnalato() != null) {
            res.setIdTeamSegnalazione(segnalazione.getTeamSegnalato().getId());
            res.setNomeTeamSegnalazione(segnalazione.getTeamSegnalato().getNome());
        }

        if (segnalazione.getMentore() != null) {
            res.setIdMentore(segnalazione.getMentore().getId());
            res.setNomeMentore(segnalazione.getMentore().getNome());
        }

        return res;
    }
}
