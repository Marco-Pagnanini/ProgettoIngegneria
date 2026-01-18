package org.example.Api.Models.Request;

import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Team;
import org.example.Core.models.User;

import java.time.LocalDate;

public class InvitoRequest {
    private Long idDelTeam;
    private Long idUtente;
    private StatoInvito stato;
    private LocalDate dataInvito;

    public Long getIdDelTeam() {
        return idDelTeam;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public StatoInvito getStato() {
        return stato;
    }

    public LocalDate getDataInvito() {
        return dataInvito;
    }

    public void setIdDelTeam(Long idDelTeam) {
        this.idDelTeam = idDelTeam;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public void setStato(StatoInvito stato) {
        this.stato = stato;
    }

    public void setDataInvito(LocalDate dataInvito) {
        this.dataInvito = dataInvito;
    }
}
