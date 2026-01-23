package org.example.Core.models;

import java.time.LocalDateTime;

public class Risposta {
    private Long id;
    private String testo;
    private Team team;
    private Hackathon hackathon;
    private SottoMissione sottoMissione;
    private LocalDateTime dataInvio;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public void setSottoMissione(SottoMissione sottoMissione) {
        this.sottoMissione = sottoMissione;
    }

    public void setDataInvio(LocalDateTime dataInvio) {
        this.dataInvio = dataInvio;
    }

    public Long getId() {
        return id;
    }

    public String getTesto() {
        return testo;
    }

    public Team getTeam() {
        return team;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public SottoMissione getSottoMissione() {
        return sottoMissione;
    }

    public LocalDateTime getDataInvio() {
        return dataInvio;
    }
}