package org.example.Api.Controllers;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneDomandaRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneProgettoRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.Hackathon;
import org.example.Core.models.sottoMissioni.SottoMissione;

import java.util.List;

public class HackathonController {
    private final IHackathonService hackathonService;
    private final ISottoMissioniService sottoMissioniService;

    public HackathonController(IHackathonService hackathonService, ISottoMissioniService sottoMissioniService) {
        this.hackathonService = hackathonService;
        this.sottoMissioniService = sottoMissioniService;
    }

    public Hackathon creazioneHackathon(HackathonRequest request) {
        return hackathonService.creazioneHackathon(request);
    }

    public List<Hackathon> visualizzaHackathon() { return hackathonService.visualizzaHackathon(); }

    public Hackathon iscrizioneTeam(Long idTeam, Long idHackathon){
        return hackathonService.iscrizioneTeam(idTeam, idHackathon);
    }

    public SottoMissione aggiungiSottoMissioneDomanda(Long idHackathon, SottoMissioneDomandaRequest request){
        return sottoMissioniService.createSottoMissioneDomanda(idHackathon,request);
    }

    public SottoMissione aggiungiSottoMissioneProgetto(Long idHackathon, SottoMissioneProgettoRequest request){
        return sottoMissioniService.createSottoMissioneProgetto(idHackathon,request);
    }
}
