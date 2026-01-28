package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.RispostaMapper;
import org.example.Api.Models.Request.RispostaRequest;
import org.example.Api.Models.Response.RispostaResponse;
import org.example.Application.Abstraction.Service.IRispostaService;
import org.example.Core.models.Risposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/risposta")
@PreAuthorize("hasAnyRole('TEAM_LEADER','TEAM_MEMBER')")
public class RispostaController {
    private final IRispostaService rispostaService;

    public RispostaController(IRispostaService rispostaService) {
        this.rispostaService = rispostaService;
    }

    @PostMapping
    public ResponseEntity<RispostaResponse> inviaRisposta(@RequestBody RispostaRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Risposta risposta = rispostaService.inviaRisposta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RispostaMapper.toResponse(risposta));
    }

    @PutMapping
    public ResponseEntity<Risposta> aggiornaRisposta(@RequestBody RispostaRequest request) {
        if (request == null || request.getIdRisposta() == null) {
            return ResponseEntity.badRequest().build();
        }
        Risposta risposta = rispostaService.aggiornaRisposta(request);
        return ResponseEntity.ok(risposta);
    }
}
