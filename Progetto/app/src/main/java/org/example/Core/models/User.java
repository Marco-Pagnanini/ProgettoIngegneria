package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
@Getter
@NoArgsConstructor
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

}
