package org.example.Core.models;

import org.example.Core.models.sottoMissioni.SottoMissione;

public class Valutazione {
    private Long id;
    private Hackathon hackathon;
    private SottoMissione sottoMissione;
    private Team team;
    private Double punteggio;

    public Long getId() {
        return id;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public SottoMissione getSottoMissione() {
        return sottoMissione;
    }

    public Team getTeam() {
        return team;
    }

    public Double getPunteggio() {
        return punteggio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public void setSottoMissione(SottoMissione sottoMissione) {
        this.sottoMissione = sottoMissione;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPunteggio(Double punteggio) {
        this.punteggio = punteggio;
    }
}
