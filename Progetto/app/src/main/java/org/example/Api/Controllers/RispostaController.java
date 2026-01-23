package org.example.Api.Controllers;

import org.example.Api.Models.Request.RispostaRequest;
import org.example.Application.Abstraction.Service.IRispostaService;
import org.example.Core.models.Risposta;

public class RispostaController {
    private final IRispostaService rispostaService;
    public RispostaController(IRispostaService rispostaService) {
        this.rispostaService = rispostaService;
    }

    public Risposta inviaRisposta(RispostaRequest request) {
        return rispostaService.inviaRisposta(request);
    }
}
