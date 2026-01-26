package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.RispostaRequest;
import org.example.Core.models.Risposta;

public interface IRispostaService {
    public Risposta inviaRisposta(RispostaRequest request);

    public Risposta aggiornaRisposta(RispostaRequest request);
}
