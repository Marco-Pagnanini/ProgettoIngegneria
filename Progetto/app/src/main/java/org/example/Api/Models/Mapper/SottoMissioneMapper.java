package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Api.Models.Response.SottoMissioneResponse;
import org.example.Core.models.SottoMissione;

public class SottoMissioneMapper {

    public static SottoMissione toEntity(SottoMissioneRequest request) {
        SottoMissione sottoMissione = new SottoMissione();
        sottoMissione.setDescrizione(request.getDescrizione());
        sottoMissione.setTitolo(request.getTitolo());
        return sottoMissione;
    }

    public static SottoMissioneResponse toResponse(SottoMissione sottoMissione) {
        SottoMissioneResponse response = new SottoMissioneResponse();
        response.setDescrizione(sottoMissione.getDescrizione());
        response.setTitolo(sottoMissione.getTitolo());
        return response;
    }
}
