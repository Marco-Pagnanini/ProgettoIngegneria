package org.example.Api.Controllers;

import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Core.models.Segnalazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/segnalazione")
public class SegnalazioneController {
    private final ISegnalazioneService segnalazioneService;

    public SegnalazioneController(ISegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    @PostMapping("/{idHackathon}")
    public ResponseEntity<Segnalazione> addSegnalazione(
            @PathVariable Long idHackathon,
            @RequestBody SegnalazioneRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Segnalazione segnalazione = segnalazioneService.inviaSegnalazione(idHackathon, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(segnalazione);
    }

    @GetMapping("/{idHackathon}")
    public ResponseEntity<List<Segnalazione>> getAllSegnalazioni(@PathVariable Long idHackathon) {
        List<Segnalazione> segnalazioni = segnalazioneService.visualizzaSegnalazione(idHackathon);
        return ResponseEntity.ok(segnalazioni);
    }

    @GetMapping("/{idHackathon}/{idSegnalazione}")
    public ResponseEntity<Segnalazione> getSegnalazioneById(
            @PathVariable Long idHackathon,
            @PathVariable Long idSegnalazione) {
        Segnalazione segnalazione = segnalazioneService.getSegnalazioneById(idHackathon, idSegnalazione);
        if (segnalazione == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(segnalazione);
    }

    @DeleteMapping("/{idHackathon}/{idSegnalazione}")
    public ResponseEntity<Segnalazione> deleteSegnalazione(
            @PathVariable Long idHackathon,
            @PathVariable Long idSegnalazione) {
        Segnalazione segnalazione = segnalazioneService.deleteSegnalazione(idHackathon, idSegnalazione);
        return ResponseEntity.ok(segnalazione);
    }
}
