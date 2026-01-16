package org.example.Api.Models.Request;

import org.example.Core.enums.State;
import org.example.Core.models.Team;
import org.example.Core.models.User;

import java.time.LocalDate;
import java.util.List;

public class HackathonRequest {
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
    private Long idGiudice;
    private List<Long> idMentori;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public void setScadenzaIscrizioni(LocalDate scadenzaIscrizioni) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
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

    public void setGiudice(Long giudice) {
        this.idGiudice = giudice;
    }

    public void setMentori(List<Long> mentori) {
        this.idMentori = mentori;
    }

    public String getNome() {
        return nome;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public String getArgomento() {
        return argomento;
    }

    public LocalDate getScadenzaIscrizioni() {
        return scadenzaIscrizioni;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public Double getPremio() {
        return premio;
    }

    public Integer getDimensioneMassimaTeam() {
        return dimensioneMassimaTeam;
    }

    public Integer getDimensioneMinimaTeam() {
        return dimensioneMinimaTeam;
    }

    public Integer getNumeroMassimoPersone() {
        return numeroMassimoPersone;
    }

    public Integer getNumeroMinimoPersone() {
        return numeroMinimoPersone;
    }

    public Long getGiudice() {
        return idGiudice;
    }

    public List<Long> getMentori() {
        return idMentori;
    }
}
