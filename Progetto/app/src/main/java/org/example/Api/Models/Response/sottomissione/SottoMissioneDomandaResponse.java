package org.example.Api.Models.Response.sottomissione;// Response per Domanda

import org.example.Api.Models.Response.SottoMissioneResponse;

public class SottoMissioneDomandaResponse extends SottoMissioneResponse {
    private String risposta;

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }
}