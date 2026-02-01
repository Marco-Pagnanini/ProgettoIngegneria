package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco Pagnanini
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {
    /**
     * identificativo univoco del team
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * nome del team
     */
    private String nome;
    /**
     * saldo del team relativo alle vincite che hanno fatto
     */
    private Double saldo = 0.0;

    /**
     * team Leader del team (colui che ha creato il team)
     * 1 Team è composto da 1 TeamLeader
     */
    @ManyToOne
    @JoinColumn(name = "team_leader_id")
    private User teamLeader;

    /**
     * membri del team che fanno parte del team
     * 1 Team è composto da Molti MembriDelTeam
     */
    @OneToMany(mappedBy = "team")
    private List<User> membriTeam = new ArrayList<>();

    /**
     * data di creazione del team
     */
    private LocalDate dataCreazione = LocalDate.now();

    /**
     * inviti mandati dal team ad utente con ruolo (UTENTE_NON_ISCRITTO)
     * 1 Team invia Molti Inviti
     */
    @OneToMany(mappedBy = "dalTeam")
    private List<Invito> inviti = new ArrayList<>();

    /**
     * Storico degli hackathon dei team
     * Molti Team partecipano a Molti Hackathon
     */
    @ManyToMany(mappedBy = "teams")
    private List<Hackathon> hackathons = new ArrayList<>();

}
