package org.example.Api.Models.Request;

public class RispostaRequest {
    private Long idHackathon;
    private Long idTeam;
    private Long idSottomissione;
    private String risposta;

    public void setIdHackathon(Long idHackathon) {
        this.idHackathon = idHackathon;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public void setIdSottomissione(Long idSottomissione) {
        this.idSottomissione = idSottomissione;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Long getIdHackathon() {
        return idHackathon;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public Long getIdSottomissione() {
        return idSottomissione;
    }

    public String getRisposta() {
        return risposta;
    }
}
