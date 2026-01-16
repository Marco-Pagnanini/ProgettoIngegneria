package org.example.utils.Builder;

import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.models.*;
import org.example.Core.enums.State;
import java.time.LocalDate;
import java.util.List;

public class HackathonBuilderImplementation implements HackathonBuilder {
    private final Hackathon hackathon;

    public HackathonBuilderImplementation(){
        this.hackathon = new Hackathon();
        this.hackathon.setStato(State.IN_PREPARAZIONE);
    }


    @Override
    public HackathonBuilder nome(String nome) {
        hackathon.setNome(nome);
        return this;
    }

    @Override
    public HackathonBuilder regolamento(String regolamento) {
        hackathon.setRegolamento(regolamento);
        return this;
    }

    @Override
    public HackathonBuilder scadenzaIscrizione(LocalDate scadenzaIscrizione) {
        hackathon.setScadenzaIscrizioni(scadenzaIscrizione);
        return this;
    }

    @Override
    public HackathonBuilder dataInizio(LocalDate dataInizio) {
        hackathon.setDataInizio(dataInizio);
        return this;
    }

    @Override
    public HackathonBuilder dataFine(LocalDate dataFine) {
        hackathon.setDataFine(dataFine);
        return this;
    }

    @Override
    public HackathonBuilder luogo(String luogo) {
        hackathon.setLuogo(luogo);
        return this;
    }

    @Override
    public HackathonBuilder premio(double premio) {
        hackathon.setPremio(premio);
        return this;
    }

    @Override
    public HackathonBuilder dimensioneMassimaTeam(int dimensioneMassimaTeam) {
        hackathon.setDimensioneMassimaTeam(dimensioneMassimaTeam);
        return this;
    }

    @Override
    public HackathonBuilder dimensioneMinimaTeam(int dimensioneMinimaTeam) {
        hackathon.setDimensioneMinimaTeam(dimensioneMinimaTeam);
        return this;
    }

    @Override
    public HackathonBuilder giudice(User giudice) {
        hackathon.setGiudice(giudice);
        return this;
    }

    @Override
    public HackathonBuilder mentori(List<User> mentori) {
        hackathon.setMentori(mentori);
        return this;
    }


    @Override
    public HackathonBuilder organizzatore(User organizzatore) {
        hackathon.setOrganizzatore(organizzatore);
        return this;
    }

    @Override
    public HackathonBuilder teams(List<Team> teams) {
        hackathon.setTeams(teams);
        return this;
    }

    @Override
    public HackathonBuilder argomento(String argomento) {
        hackathon.setArgomento(argomento);
        return this;
    }

    @Override
    public HackathonBuilder sottomissioni(List<String> sottomissioni) {
        hackathon.setSottomissioni(sottomissioni);
        return this;
    }

    @Override
    public HackathonBuilder numeroMassimoPersone(int numeroMassimoPersone) {
        hackathon.setNumeroMassimoPersone(numeroMassimoPersone);
        return this;
    }

    @Override
    public HackathonBuilder numeroMinimoPersone(int numeroMinimoPersone) {
        hackathon.setNumeroMinimoPersone(numeroMinimoPersone);
        return this;
    }

    public Hackathon build() {
        //DA CAPIRE SE INSERIRE UNA SORTA DI VALIDAZIONE
        return hackathon;
    }
}