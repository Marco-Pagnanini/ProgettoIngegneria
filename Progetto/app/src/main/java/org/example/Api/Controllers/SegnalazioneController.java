package org.example.Api.Controllers;

import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Core.models.Segnalazione;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/segnalazione")
public class SegnalazioneController {
    private final ISegnalazioneService segnalazioneService;

    public SegnalazioneController(ISegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    @PostMapping
    public Segnalazione addSegnalazione(@RequestBody Long idHackathon, @RequestBody SegnalazioneRequest request) {
        return segnalazioneService.inviaSegnalazione(idHackathon, request);
    }

    @GetMapping("/{idHackathon}")
    public List<Segnalazione> getAllSegnalazioni(@PathVariable Long idHackathon){
       return segnalazioneService.visualizzaSegnalazione(idHackathon);
    }
}
