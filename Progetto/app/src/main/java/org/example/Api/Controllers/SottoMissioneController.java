package org.example.Api.Controllers;

import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.SottoMissione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sottomissione")
public class SottoMissioneController {
    private final ISottoMissioniService sottoMissioniService;

    public SottoMissioneController(ISottoMissioniService service) {
        this.sottoMissioniService = service;
    }

    @PostMapping("/{idHackathon}")
    @PreAuthorize("hasAnyRole('TEAM_MEMBER', 'TEAM_LEADER')")
    public ResponseEntity<SottoMissione> aggiungiSottoMissione(
            @PathVariable Long idHackathon,
            @RequestBody SottoMissioneRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        SottoMissione sottoMissione = sottoMissioniService.createSottoMissione(idHackathon, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sottoMissione);
    }

    @GetMapping("/{idHackathon}")
    public ResponseEntity<List<SottoMissione>> visualizzaSottoMissione(@PathVariable Long idHackathon) {
        List<SottoMissione> sottoMissioni = sottoMissioniService.visualizzaSottoMissione(idHackathon);
        return ResponseEntity.ok(sottoMissioni);
    }
}
