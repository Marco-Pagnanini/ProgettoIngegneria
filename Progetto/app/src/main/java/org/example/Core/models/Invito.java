package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoInvito;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Invito {
    /**
     * identificativo univoco dell'Invito
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * titolo dell'invito (non implementato)
     */
    private String titolo;
    /**
     * descrizione dell'invito (non implementato)
     */
    private String descrizione;

    /**
     * team che invia l'invito
     * Molti Inviti sono inviati da 1 Team
     *
     */
    @ManyToOne
    @JoinColumn(name = "dal_team_id")
    private Team dalTeam;

    /**
     * user (con ruolo UTENTE_NON_ISCRITTO) invitato a partecipare ad un team
     * Molti Inviti sono ricevuti da 1 User
     */
    @ManyToOne
    @JoinColumn(name = "per_utente_id")
    private User perUtente;

    /**
     * stato dell'invito del team
     */
    @Enumerated(EnumType.STRING)
    private StatoInvito stato;

    /**
     * data dell'invio dell'invito
     */
    private LocalDate dataInvito = LocalDate.now();

}
