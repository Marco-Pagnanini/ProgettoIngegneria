package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.InvitoMapper;
import org.example.Api.Models.Mapper.SegnalazioneMapper;
import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Api.Models.Response.InvitoResponse;
import org.example.Api.Models.Response.SegnalazioneResponse;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Application.Service.SegnalazioneService;
import org.example.Core.models.Invito;
import org.example.Core.models.Segnalazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/segnalazione")
public class SegnalazioneController {
    private final ISegnalazioneService segnalazioneService;

    public SegnalazioneController(ISegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    @PostMapping("/{idHackathon}")
    @PreAuthorize("hasRole('MENTORE')")
    public ResponseEntity<SegnalazioneResponse> addSegnalazione(
            @PathVariable Long idHackathon,
            @RequestBody SegnalazioneRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Segnalazione segnalazione = segnalazioneService.inviaSegnalazione(idHackathon, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(SegnalazioneMapper.toResponse(segnalazione));
    }

    @GetMapping("/{idHackathon}")

    public ResponseEntity<List<SegnalazioneResponse>> getAllSegnalazioni(@PathVariable Long idHackathon) {
        List<Segnalazione> segnalazione = segnalazioneService.getAllSegnalazioni();
        List<SegnalazioneResponse> response = new ArrayList<>();
        for(Segnalazione s : segnalazione) {
            response.add(SegnalazioneMapper.toResponse(s));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idHackathon}/{idSegnalazione}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<SegnalazioneResponse> getSegnalazioneById(
            @PathVariable Long idHackathon,
            @PathVariable Long idSegnalazione) {
        Segnalazione segnalazione = segnalazioneService.getSegnalazioneById(idHackathon, idSegnalazione);
        return ResponseEntity.ok(SegnalazioneMapper.toResponse(segnalazione));

    }

    @DeleteMapping("/{idHackathon}/{idSegnalazione}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<SegnalazioneResponse> deleteSegnalazione(
            @PathVariable Long idHackathon,
            @PathVariable Long idSegnalazione) {
        Segnalazione s = segnalazioneService.deleteSegnalazione(idHackathon, idSegnalazione);
        return ResponseEntity.ok(SegnalazioneMapper.toResponse(s));
    }
}
