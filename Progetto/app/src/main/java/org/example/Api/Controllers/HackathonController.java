package org.example.Api.Controllers;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hackathon")
public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @PostMapping
    public Hackathon creazioneHackathon(HackathonRequest request) {
        return hackathonService.creazioneHackathon(request);
    }

    @GetMapping
    public List<Hackathon> visualizzaHackathon() { return hackathonService.visualizzaHackathon(); }

    
    public Hackathon iscrizioneTeam(Long idTeam, Long idHackathon){
        return hackathonService.iscrizioneTeam(idTeam, idHackathon);
    }

    public Hackathon visualizzaHackathonById(Long idHackathon) {
        return hackathonService.getHackathonById(idHackathon);
    }

}
