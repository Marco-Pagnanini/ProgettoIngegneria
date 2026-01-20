package org.example.Core.models;

import org.example.Core.enums.StatoSegnalazione;

import java.time.LocalDateTime;

public class Segnalazione {
    private Long id;
    private String nome;
    private String descrizione;
    private StatoSegnalazione  statoSegnalazione;
    private Team teamSegnalato;
    private UserStaff mentore;
    private Hackathon hackathon;
    private LocalDateTime dataCreazione;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Team getTeamSegnalato() {
        return teamSegnalato;
    }

    public UserStaff getMentore() {
        return mentore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTeamSegnalato(Team teamSegnalato) {
        this.teamSegnalato = teamSegnalato;
    }

    public void setMentore(UserStaff mentore) {
        this.mentore = mentore;
    }

    public Long getHackathonId() {
        return hackathon.getId();
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public StatoSegnalazione getStatoSegnalazione() {
        return statoSegnalazione;
    }

    public void setStatoSegnalazione(StatoSegnalazione statoSegnalazione) {
        this.statoSegnalazione = statoSegnalazione;
    }
}
