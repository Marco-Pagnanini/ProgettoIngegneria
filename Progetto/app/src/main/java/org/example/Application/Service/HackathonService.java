package org.example.Application.Service;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.RuoloStaff;
import org.example.Core.enums.State;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;
import org.example.utils.Builder.HackathonBuilderImplementation;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HackathonService implements IHackathonService {

    private final IUnitOfWork unitOfWork;
    private final Validator<Hackathon> hackathonValidator;


    public HackathonService(IUnitOfWork unitOfWork, Validator<Hackathon> hackathonValidator) {
        this.unitOfWork = unitOfWork;
        this.hackathonValidator = hackathonValidator;
    }

    @Override
    public Hackathon creazioneHackathon(HackathonRequest request) {
        UserStaff giudice = unitOfWork.userStaffRepository().getById(request.getIdGiudice());

        List<UserStaff> mentori = new ArrayList<>();
        for(Long idMentore : request.getIdMentori()) {
            UserStaff mentore = unitOfWork.userStaffRepository().getById(idMentore);
            mentori.add(mentore);
        }

        Hackathon hackathon = new HackathonBuilderImplementation()
                .nome(request.getNome())
                .regolamento(request.getRegolamento())
                .argomento(request.getArgomento())
                .scadenzaIscrizione(request.getScadenzaIscrizioni())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .luogo(request.getLuogo())
                .premio(request.getPremio())
                .dimensioneMassimaTeam(request.getDimensioneMassimaTeam())
                .dimensioneMinimaTeam(request.getDimensioneMinimaTeam())
                .numeroMassimoPersone(request.getNumeroMassimoPersone())
                .numeroMinimoPersone(request.getNumeroMinimoPersone())
                .giudice(giudice)
                .mentori(mentori)
                .build();

        if(!hackathonValidator.validate(hackathon)){
            throw new IllegalArgumentException();
        }

        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();

        return hackathon;
    }

    @Override
    public Hackathon updateHackathon(Hackathon hackathon) {
        if(!hackathonValidator.validate(hackathon)) {throw new IllegalArgumentException();}
        Hackathon response = unitOfWork.hackathonRepository().update(hackathon);
        if(response == null) {throw new IllegalArgumentException();}
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon deleteHackathon(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().delete(id);
        if(response == null) {throw new IllegalArgumentException();}
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon getHackathonById(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().getById(id);
        if(response == null) {throw new IllegalArgumentException();}
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Hackathon> visualizzaHackathon() {
        List<Hackathon> response = unitOfWork.hackathonRepository().getAll();
        if(response == null) {throw new IllegalArgumentException();}
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon iscrizioneTeam(Long idTeam, Long idHackathon) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);

        Team toFind = unitOfWork.teamRepository().getById(idTeam);

        if(hackathon == null || toFind == null) {throw new IllegalArgumentException();}

        //STATE
        if(hackathon.getStato() != State.IN_ISCRIZIONE) {throw new IllegalArgumentException();}

        if(hackathon.getTeams().contains(toFind)) {throw new IllegalArgumentException();}

        if(hackathon.getDimensioneMassimaTeam() < toFind.getMembriTeam().size() + 1) {throw new IllegalArgumentException();}
        if(hackathon.getDimensioneMinimaTeam() > toFind.getMembriTeam().size() + 1) throw new IllegalArgumentException();

        List<Team> teams = hackathon.getTeams();

        if(hackathon.getNumeroMassimoPersone() < numPersone(teams) + toFind.getMembriTeam().size() + 1) throw new IllegalArgumentException();

        teams.add(toFind);
        hackathon.setTeams(teams);

        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return hackathon;
    }

    private int numPersone(List<Team> teams){
        int count = 0;
        for(Team team : teams){
            count += team.getMembriTeam().size() + 1;
        }
        return count;
    }
}
