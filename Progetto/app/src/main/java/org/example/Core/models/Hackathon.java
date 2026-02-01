package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.State;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "hackathon")
public class Hackathon {
    /**
     * identificativo univoco dell'hackthon
     * strategy = identificativa per incrementare automaticamente l'id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * nome dell'hackathon
     */
    private String nome;
    /**
     * regolamento scritto dell'hackathon
     */
    private String regolamento;
    /**
     * tema o argomento dell'hackathon
     */
    private String argomento;
    /**
     * la data in cui i partecipanti si possono iscrivere
     */
    private LocalDate scadenzaIscrizioni;
    /**
     * data di inizio dell'hackathon
     */
    private LocalDate dataInizio;
    /**
     * data di fine dell'hackathon
     */
    private LocalDate dataFine;
    /**
     * il luogo di dove si svolgerà l'hackathon
     */
    private String luogo;
    /**
     * premio monetario dell'hackathon
     */
    private Double premio;
    /**
     * dimensione massima del team
     */
    private Integer dimensioneMassimaTeam;
    /**
     * dimensione minima del team
     */
    private Integer dimensioneMinimaTeam;
    /**
     * numero massimo di persone partecipanti
     */
    private Integer numeroMassimoPersone;
    /**
     * numero minimo di persone partecipanti per avviare l'hackathon
     */
    private Integer numeroMinimoPersone;

    /**
     * organizzatore dell'hackathon (UserStaff con ruolo Organizzatore)
     * 1 Hackathon è organizzato da 1 Organizzatore (ManyToOne perché un organizzatore può organizzare più hackathon)
     */
    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private UserStaff organizzatore;

    /**
     * giudice dell'hackathon (UserStaff con ruolo Giudice)
     *  1 Hackathon è valutato da 1 Giudice (ManyToOne perché un giudice può valutare più hackathon)
     */
    @ManyToOne
    @JoinColumn(name = "giudice_id")
    private UserStaff giudice;

    /**
     * insieme di mentori che monitorano l'hackathon
     * Molti Mentori supportano Molti Hackathon
     * crea una classe associata "hackathon_mentori" per il rapporto molti a molti
     */
    //
    @ManyToMany
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private List<UserStaff> mentori = new ArrayList<>();

    /**
     * insieme di team che partecipano all'hackathon
     * Molti Team partecipano a Molti Hackathon
     * crea una classe associata "hackathon_team" per il rapporto molti a molti
     */
    @ManyToMany
    @JoinTable(
            name = "hackathon_teams",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    /**
     * il team vincitore dell'hackathon
     * 1 Hackathon ha 1 Vincitore ( inizialmente null)
     */
    //
    @OneToOne
    @JoinColumn(name = "vincitore_id")
    private Team vincitore;

    /**
     * indica lo stato dell'hackathon (IN_CORSO, IN_PREPARAZIONE,...)
     */
    @Enumerated(EnumType.STRING)
    private State stato;

    /**
     * insieme delle segnalazioni presenti nell'hackathon
     * 1 Hackathon contiene Molte Segnalazioni
     */
    @OneToMany(mappedBy = "hackathon")
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    /**
     * insieme di tutte le task/domande che sono presenti da svolgere nell'hackathon
     * 1 Hackathon contiene Molte SottoMissioni
     */
    @OneToMany(mappedBy = "hackathon")
    private List<SottoMissione> sottoMissioni = new ArrayList<>();

    /**
     * insieme di tutte le risposta date alle sottomissioni di quell'hackathon
     * 1 Hackathon contiene Molte Risposte
     */
    @OneToMany(mappedBy = "hackathon")
    private List<Risposta> risposte = new ArrayList<>();

}
