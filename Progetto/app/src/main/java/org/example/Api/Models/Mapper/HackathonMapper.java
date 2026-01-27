package org.example.Api.Models.Mapper;

import org.example.Api.Models.Response.HackathonResponse;
import org.example.Api.Models.Response.InvitoResponse;
import org.example.Core.models.Hackathon;
import org.example.utils.Builder.HackathonBuilderImplementation;

public class HackathonMapper {
    public static HackathonResponse toResponse(Hackathon  hackathon) {
        HackathonResponse hackathonResponse = new HackathonResponse();
        hackathonResponse.setNome(hackathon.getNome());
        hackathonResponse.setRegolamento(hackathon.getRegolamento());
        hackathonResponse.setArgomento(hackathon.getArgomento());
        hackathonResponse.setScadenzaIscrizioni(hackathon.getScadenzaIscrizioni());
        hackathonResponse.setDataInizio(hackathon.getDataInizio());
        hackathonResponse.setDataFine(hackathon.getDataFine());
        hackathonResponse.setLuogo(hackathon.getLuogo());
        hackathonResponse.setPremio(hackathon.getPremio());
        hackathonResponse.setDimensioneMassimaTeam(hackathon.getDimensioneMassimaTeam());
        hackathonResponse.setDimensioneMinimaTeam(hackathon.getDimensioneMinimaTeam());
        hackathonResponse.setNumeroMinimoPersone(hackathon.getNumeroMinimoPersone());
        hackathonResponse.setNumeroMassimoPersone(hackathon.getNumeroMassimoPersone());
        return  hackathonResponse;
    }

}
