package org.example.Api.Controllers;

import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Core.models.Segnalazione;

import java.util.List;

public class SegnalazioneController {
    private final ISegnalazioneService segnalazioneService;

    public SegnalazioneController(ISegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    public Segnalazione addSegnalazione(Long idHackathon, SegnalazioneRequest request) {
        return segnalazioneService.inviaSegnalazione(idHackathon, request);
    }

    public List<Segnalazione> getAllSegnalazioni(Long idHackathon){
       return segnalazioneService.visualizzaSegnalazione(idHackathon);
    }
}
