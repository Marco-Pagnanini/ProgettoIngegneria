package org.example.Core.models;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Marco Pagnanini
 */
public class Team {
    private Long id;
    private String nome;
    private User teamLeader;
    private List<User> membriTeam;
    private LocalDate dataCreazione;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public List<User> getMembriTeam() {
        return membriTeam;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    public void setMembriTeam(List<User> membriTeam) {
        this.membriTeam = membriTeam;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
