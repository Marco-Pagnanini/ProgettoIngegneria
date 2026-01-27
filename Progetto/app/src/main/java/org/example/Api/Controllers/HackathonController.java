package org.example.Api.Controllers;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hackathon")
public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @PostMapping
    public ResponseEntity<Hackathon> creazioneHackathon(@RequestBody HackathonRequest request) {
        return ResponseEntity.ok(hackathonService.creazioneHackathon(request));
    }

    @GetMapping
    public ResponseEntity<List<Hackathon>> visualizzaHackathon() {
        return ResponseEntity.ok(hackathonService.visualizzaHackathon());
    }

    @PutMapping("/iscrizione/{idTeam}/{idHackathon}")
    public ResponseEntity<Hackathon> iscrizioneTeam(@PathVariable  Long idTeam, @PathVariable Long idHackathon){
        return ResponseEntity.ok(hackathonService.iscrizioneTeam(idTeam, idHackathon));
    }

    @GetMapping("/{idHackathon}")
    public ResponseEntity<Hackathon> visualizzaHackathonById(@PathVariable Long idHackathon) {
        return ResponseEntity.ok(hackathonService.getHackathonById(idHackathon));
    }

}
