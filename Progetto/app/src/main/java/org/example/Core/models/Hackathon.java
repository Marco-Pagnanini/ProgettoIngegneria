package org.example.Core.models;

import org.example.Core.enums.State;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Hackathon() {
        segnalazioni = new ArrayList<>();
        teams =  new ArrayList<>();
        sottoMissioni =  new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRegolamento() {
        return regolamento;
    }
    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }
    public LocalDate getScadenzaIscrizioni() {
        return scadenzaIscrizioni;
    }

    public void setScadenzaIscrizioni(LocalDate scadenzaIscrizioni) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setDimensioneMassimaTeam(int dimensioneMassimaTeam) {
        this.dimensioneMassimaTeam = dimensioneMassimaTeam;
    }

    public int getDimensioneMassimaTeam() {
        return dimensioneMassimaTeam;
    }

    public List<SottoMissione> getSottoMissioni() {
        return sottoMissioni;
    }

    public List<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public int getDimensioneMinimaTeam() {
        return dimensioneMinimaTeam;
    }

    public void setDimensioneMinimaTeam(int dimensioneMinimaTeam) {
        this.dimensioneMinimaTeam = dimensioneMinimaTeam;
    }

    public void setGiudice(UserStaff giudice) {
        this.giudice = giudice;
    }

    public UserStaff getGiudice() {
        return giudice;
    }

    public void setMentori(List<UserStaff> mentori) {
        this.mentori = mentori;
    }

    public List<UserStaff> getMentori() {
        return mentori;
    }

    public Team getVincitore() {
        return vincitore;
    }

    public void setVincitore(Team vincitore) {
        this.vincitore = vincitore;
    }

    public void setStato(State stato) {
        this.stato = stato;
    }

    public State getStato() {
        return stato;
    }

    public void setOrganizzatore(UserStaff organizzatore) {
        this.organizzatore = organizzatore;
    }

    public UserStaff getOrganizzatore() {
        return organizzatore;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setSottomissioni(List<SottoMissione> sottoMissioni) {
        this.sottoMissioni = sottoMissioni;
    }

    public List<SottoMissione> getSottomissioni() {
        return sottoMissioni;
    }

    public void setNumeroMinimoPersone(int numeroMinimoPersone) {
        this.numeroMinimoPersone = numeroMinimoPersone;
    }

    public int getNumeroMinimoPersone() {
        return numeroMinimoPersone;
    }

    public void setNumeroMassimoPersone(int numeroMassimoPersone) {
        this.numeroMassimoPersone = numeroMassimoPersone;
    }

    public int getNumeroMassimoPersone() {
        return numeroMassimoPersone;
    }

    public void setPremio(Double premio) {
        this.premio = premio;
    }

    public void setDimensioneMassimaTeam(Integer dimensioneMassimaTeam) {
        this.dimensioneMassimaTeam = dimensioneMassimaTeam;
    }

    public void setDimensioneMinimaTeam(Integer dimensioneMinimaTeam) {
        this.dimensioneMinimaTeam = dimensioneMinimaTeam;
    }

    public void setNumeroMassimoPersone(Integer numeroMassimoPersone) {
        this.numeroMassimoPersone = numeroMassimoPersone;
    }

    public void setNumeroMinimoPersone(Integer numeroMinimoPersone) {
        this.numeroMinimoPersone = numeroMinimoPersone;
    }

    public void setSottoMissioni(List<SottoMissione> sottoMissioni) {
        this.sottoMissioni = sottoMissioni;
    }

    public void setSegnalazioni(List<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }
}
