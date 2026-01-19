package org.example.Api.Models.Request;

import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;

public class SegnalazioneRequest {
    private Long id;
    private String nome;
    private String descrizione;
    private Long idTeamSegnalazione;
    private Long idMentore;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdTeamSegnalazione(Long idTeamSegnalazione) {
        this.idTeamSegnalazione = idTeamSegnalazione;
    }

    public void setIdMentore(Long idMentore) {
        this.idMentore = idMentore;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Long getIdTeamSegnalazione() {
        return idTeamSegnalazione;
    }

    public Long getIdMentore() {
        return idMentore;
    }
}
