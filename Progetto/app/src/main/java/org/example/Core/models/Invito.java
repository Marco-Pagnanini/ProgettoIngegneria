package org.example.Core.models;

import org.example.Core.enums.StatoInvito;

import java.time.LocalDate;

public class Invito {
    private Long id;
    private Team dalTeam;
    private User perUtente;
    private StatoInvito stato;
    private LocalDate dataInvito;

    public Long getId() {
        return id;
    }

    public Team getDalTeam() {
        return dalTeam;
    }

    public User getPerUtente() {
        return perUtente;
    }

    public StatoInvito getStato() {
        return stato;
    }

    public LocalDate getDataInvito() {
        return dataInvito;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDalTeam(Team dalTeam) {
        this.dalTeam = dalTeam;
    }

    public void setPerUtente(User perUtente) {
        this.perUtente = perUtente;
    }

    public void setStato(StatoInvito stato) {
        this.stato = stato;
    }

    public void setDataInvito(LocalDate dataInvito) {
        this.dataInvito = dataInvito;
    }
}
