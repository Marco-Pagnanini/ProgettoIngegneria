package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.sottomissione.SottoMissioneDomandaRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneProgettoRequest;
import org.example.Core.models.sottoMissioni.SottoMissione;

public interface ISottoMissioniService {
    SottoMissione createSottoMissioneDomanda(Long idHackathon, SottoMissioneDomandaRequest request);
    SottoMissione createSottoMissioneProgetto(Long idHackathon, SottoMissioneProgettoRequest request);
}
