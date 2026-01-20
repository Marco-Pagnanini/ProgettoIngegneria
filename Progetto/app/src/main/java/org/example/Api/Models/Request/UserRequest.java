package org.example.Api.Models.Request;

import java.time.LocalDate;

public class UserRequest {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String cellulare;
    private LocalDate dataDiNascita;
    private LocalDate dataCreazione;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }
}
