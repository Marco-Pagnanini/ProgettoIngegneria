package org.example.Api.Controllers;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;

import java.util.List;

public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    public Hackathon creazioneHackathon(HackathonRequest request) {
        return hackathonService.creazioneHackathon(request);
    }

    public List<Hackathon> visualizzaHackathon() { return hackathonService.visualizzaHackathon(); }

    public Hackathon iscrizioneTeam(Long idTeam, Long idHackathon){
        return hackathonService.iscrizioneTeam(idTeam, idHackathon);
    }

    public Hackathon visualizzaHackathonById(Long idHackathon) {
        return hackathonService.getHackathonById(idHackathon);
    }

}
