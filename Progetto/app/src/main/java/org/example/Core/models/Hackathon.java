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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String regolamento;
    private String argomento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private Double premio;
    private Integer dimensioneMassimaTeam;
    private Integer dimensioneMinimaTeam;
    private Integer numeroMassimoPersone;
    private Integer numeroMinimoPersone;

    // 1 Hackathon è organizzato da 1 Organizzatore (ManyToOne perché un organizzatore può organizzare più hackathon)
    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private UserStaff organizzatore;

    // 1 Hackathon è valutato da 1 Giudice (ManyToOne perché un giudice può valutare più hackathon)
    @ManyToOne
    @JoinColumn(name = "giudice_id")
    private UserStaff giudice;

    // Molti Mentori supportano Molti Hackathon
    @ManyToMany
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private List<UserStaff> mentori = new ArrayList<>();

    // Molti Team partecipano a Molti Hackathon
    @ManyToMany
    @JoinTable(
            name = "hackathon_teams",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    // 1 Hackathon ha 1 Vincitore (può essere null)
    @OneToOne
    @JoinColumn(name = "vincitore_id")
    private Team vincitore;

    @Enumerated(EnumType.STRING)
    private State stato;

    // 1 Hackathon contiene Molte Segnalazioni
    @OneToMany(mappedBy = "hackathon")
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    // 1 Hackathon contiene Molte SottoMissioni
    @OneToMany(mappedBy = "hackathon")
    private List<SottoMissione> sottoMissioni = new ArrayList<>();

    // 1 Hackathon contiene Molte Risposte
    @OneToMany(mappedBy = "hackathon")
    private List<Risposta> risposte = new ArrayList<>();

}
