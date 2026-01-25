package org.example.Core.models;

import org.example.Core.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entità User:
 *
 * @author Marco Pagnanini
 */
public class User {
    /**
     * identificativo univoco per l'utente
     * future implementazioni: usare {@link java.util.UUID} per una maggiore sicurezza
      */
    private Long id;
    /**
     * nome dell'utente
     */
    private String nome;
    /**
     * cognome dell'utente
     */
    private String cognome;
    /**
     * email dell'utente
     * future implementazioni : validatore per l'email
     */
    private String email;
    /**
     * password crittografata dell'utente
     */
    private String password;
    /**
     * numero telefonico dell'utente
     * future implementazioni : possibilità di verificare tramite sms l'utente
     */
    private String cellulare;
    /**
     * data di nascita dell'utente
     */
    private LocalDate dataNascita;
    /**
     * data di quando l'utente crea l'account
     */
    private LocalDateTime dataCreazione;
    /**
     * Ruolo dell'utente
     */
    private RuoloUser ruolo;

    private Team team;
    private List<Hackathon> hackathons = new ArrayList<>();

    private List<Invito> inviti = new ArrayList<>();

    public User(Long id, RuoloUser ruoloUser) {
        this.id = id;
        this.ruolo = ruoloUser;
    }

    public void setInviti(List<Invito> inviti) {
        this.inviti = inviti;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public void setRuolo(RuoloUser ruolo) {
        this.ruolo = ruolo;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setHackathons(List<Hackathon> hackathons) {
        this.hackathons = hackathons;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCellulare() {
        return cellulare;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public RuoloUser getRuolo() {
        return ruolo;
    }

    public Team getTeam() {
        return team;
    }

    public List<Hackathon> getHackathons() {
        return hackathons;
    }
    public List<Invito> getInviti() {
        return inviti;
    }
}
