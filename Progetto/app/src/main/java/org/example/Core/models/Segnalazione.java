package org.example.Core.models;

import org.example.Core.enums.StatoSegnalazione;

public class Segnalazione {
    private Long id;
    private String nome;
    private String descrizione;
    private StatoSegnalazione  statoSegnalazione;
    private Team teamSegnalato;
    private UserStaff mentore;
    private Hackathon hackathon;

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
}
