package org.example.Api.Models.Request.sottomissione;

public class SottoMissioneDomandaRequest extends SottoMissioneRequest {

    private String risposta;
    private Integer limiteCaratteri;

    @Override
    public String getTipoSottomissione() {
        return "DOMANDA_APERTA";
    }

    // Getters e Setters
    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Integer getLimiteCaratteri() {
        return limiteCaratteri;
    }

    public void setLimiteCaratteri(Integer limiteCaratteri) {
        this.limiteCaratteri = limiteCaratteri;
    }
}