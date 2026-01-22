package org.example.Application.Abstraction.Service;

import org.example.Core.models.SottoMissione;

public interface ISottoMissioniService {
    SottoMissione createSottoMissione(Long idHackathon,SottoMissione sottoMissione);
}
