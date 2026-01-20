package org.example.Api.Models.Response;

import org.example.Core.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO di risposta per l'entità User
 * Non include dati sensibili come la password
 *
 * @author Marco Pagnanini
 */
public class UserResponse {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String cellulare;
    private LocalDate dataNascita;
    private LocalDateTime dataCreazione;
    private RuoloUser ruolo;

    // Dati del team se presente
    private Long teamId;
    private String teamNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public RuoloUser getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloUser ruolo) {
        this.ruolo = ruolo;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamNome() {
        return teamNome;
    }

    public void setTeamNome(String teamNome) {
        this.teamNome = teamNome;
    }
}