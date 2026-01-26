package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.RuoloStaff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entità UserStaff:
 * gestisce tutti i MembriDelloStaff
 *
 * @author Marco Pagnanini
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserStaff {
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
    private RuoloStaff ruolo;

    // 1 Organizzatore organizza Molti Hackathon (lato inverso)
    @OneToMany(mappedBy = "organizzatore")
    private List<Hackathon> hackathonOrganizzati = new ArrayList<>();

    // 1 Giudice valuta Molti Hackathon (lato inverso)
    @OneToMany(mappedBy = "giudice")
    private List<Hackathon> hackathonValutati = new ArrayList<>();

    // Molti Mentori supportano Molti Hackathon (lato inverso)
    @ManyToMany(mappedBy = "mentori")
    private List<Hackathon> hackathonSupportati = new ArrayList<>();

    // 1 Mentore riceve Molte Segnalazioni
    @OneToMany(mappedBy = "mentore")
    private List<Segnalazione> segnalazioniRicevute = new ArrayList<>();

}
