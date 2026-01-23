package org.example.Core.models.sottoMissioni;

public class SottoMissioneDomanda extends SottoMissione{
    private String risposta;

    public String getRisposta() {
        return risposta;
    }
    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    @Override
    public String getTipo() {
        return "Domanda Aperta";
    }
}
