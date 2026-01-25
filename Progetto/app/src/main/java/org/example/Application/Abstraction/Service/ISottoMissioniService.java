package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Core.models.SottoMissione;

import java.util.List;

public interface ISottoMissioniService {
    SottoMissione createSottoMissione(Long idHackathon, SottoMissioneRequest sottoMissione);
    List<SottoMissione> visualizzaSottoMissione(Long idHackathon);
}
