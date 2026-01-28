package org.example.Api.Controllers;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.example.Api.Models.Mapper.HackathonMapper;
import org.example.Api.Models.Mapper.InvitoMapper;
import org.example.Api.Models.Mapper.SegnalazioneMapper;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Api.Models.Response.HackathonResponse;
import org.example.Api.Models.Response.InvitoResponse;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Invito;
import org.example.Core.models.Segnalazione;
import org.example.Core.models.SottoMissione;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/hackathon")
public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @PostMapping("/{idOrganizzatore}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonResponse> creazioneHackathon(@PathVariable Long idOrganizzatore,@RequestBody HackathonRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        Hackathon hackathon = hackathonService.creazioneHackathon(idOrganizzatore,request);
        return ResponseEntity.ok(HackathonMapper.toResponse(hackathon));
    }

    @GetMapping
    public ResponseEntity<List<HackathonResponse>> visualizzaHackathon() {
        List<Hackathon> hackathon = hackathonService.getAllHackathon();
        List<HackathonResponse> response = new ArrayList<>();
        for(Hackathon h : hackathon) {
            response.add(HackathonMapper.toResponse(h));
        }
        return ResponseEntity.ok(response);

    }

    @PutMapping("/iscrizione/{idTeam}/{idHackathon}")
    @PreAuthorize("hasRole('TEAM_LEADER')")
    public ResponseEntity<HackathonResponse> iscrizioneTeam(@PathVariable  Long idTeam, @PathVariable Long idHackathon){
        Hackathon hackathon = hackathonService.iscrizioneTeam(idTeam,idHackathon);
        return ResponseEntity.ok(HackathonMapper.toResponse(hackathon));

    }

    @GetMapping("/{idHackathon}")
    public ResponseEntity<HackathonResponse> visualizzaHackathonById(@PathVariable Long idHackathon) {
        Hackathon hackathon =  hackathonService.getHackathonById(idHackathon);
        return ResponseEntity.ok(HackathonMapper.toResponse(hackathon));
    }

}
