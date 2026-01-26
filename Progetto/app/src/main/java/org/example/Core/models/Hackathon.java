package org.example.Core.models;

import lombok.AllArgsConstructor;
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
public class Hackathon {
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
    private UserStaff organizzatore;
    private UserStaff giudice;
    private List<UserStaff> mentori;
    private List<Team> teams;
    private Team vincitore;
    private State stato;
    private List<Segnalazione> segnalazioni;
    private List<SottoMissione> sottoMissioni;


}
