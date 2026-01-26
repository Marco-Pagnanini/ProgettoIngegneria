package org.example.Api.Controllers;

import org.example.Api.Models.Request.RispostaRequest;
import org.example.Application.Abstraction.Service.IRispostaService;
import org.example.Core.models.Risposta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/risposta")
public class RispostaController {
    private final IRispostaService rispostaService;

    public RispostaController(IRispostaService rispostaService) {
        this.rispostaService = rispostaService;
    }

    @PostMapping
    public Risposta inviaRisposta(RispostaRequest request) {
        return rispostaService.inviaRisposta(request);
    }
    @PutMapping
    public Risposta aggiornaRisposta(RispostaRequest request){return rispostaService.aggiornaRisposta(request);}
}
