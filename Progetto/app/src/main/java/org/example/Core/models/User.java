package org.example.Core.models;

import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class User {
    /**
     * identificativo univoco per l'utente
     * future implementazioni: usare {@link java.util.UUID} per una maggiore sicurezza
      */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private RuoloUser ruolo = RuoloUser.UTENTE_NON_ISCRITTO;

    // Molti User (MembriDelTeam) appartengono a 1 Team
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // 1 User riceve Molti Inviti
    @OneToMany(mappedBy = "perUtente")
    private List<Invito> inviti = new ArrayList<>();

}
