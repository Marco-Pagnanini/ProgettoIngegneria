package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Core.models.SottoMissione;

public class SottoMissioneMapper {

    public static SottoMissione toEntity(SottoMissioneRequest request) {
        SottoMissione sottoMissione = new SottoMissione();
        sottoMissione.setId(1L);
        sottoMissione.setDescrizione(request.getDescrizione());
        sottoMissione.setTitolo(request.getTitolo());
        return sottoMissione;
    }
}
