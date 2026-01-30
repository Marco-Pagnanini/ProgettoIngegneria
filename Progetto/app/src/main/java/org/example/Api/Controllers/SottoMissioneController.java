package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.SottoMissioneMapper;
import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Api.Models.Response.SottoMissioneResponse;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.SottoMissione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/sottomissione")
public class SottoMissioneController {
    private final ISottoMissioniService sottoMissioniService;

    public SottoMissioneController(ISottoMissioniService service) {
        this.sottoMissioniService = service;
    }

    @PostMapping("/{idHackathon}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<SottoMissioneResponse> aggiungiSottoMissione(
            @PathVariable Long idHackathon,
            @RequestBody SottoMissioneRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        SottoMissione sottoMissione = sottoMissioniService.createSottoMissione(idHackathon, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(SottoMissioneMapper.toResponse(sottoMissione));
    }

    @PreAuthorize("hasAnyRole('TEAM_MEMBER','TEAM_LEADER','ORGANIZZATORE','MENTORE', 'GIUDICE')")
    @GetMapping("/{idHackathon}")
    public ResponseEntity<List<SottoMissioneResponse>> visualizzaSottoMissione(@PathVariable Long idHackathon) {
        List<SottoMissione> sottoMissioni = sottoMissioniService.visualizzaSottoMissione(idHackathon);
        List<SottoMissioneResponse> response = new ArrayList<>();

        for(SottoMissione m : sottoMissioni) {
            response.add(SottoMissioneMapper.toResponse(m));
        }
        return ResponseEntity.ok(response);
    }
}
