package org.example.Application.Service;

import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.RuoloStaff;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Segnalazione;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class SegnalazioneService implements ISegnalazioneService {
    private IUnitOfWork unitOfWork;
    private Validator<Segnalazione> validator;
    public SegnalazioneService(IUnitOfWork unitOfWork, Validator<Segnalazione> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Segnalazione addSegnalazione(Long idHackathon, SegnalazioneRequest request) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) return null;

        Team team = unitOfWork.teamRepository().getById(request.getIdTeamSegnalazione());
        if(team == null) return null;


        UserStaff mentore = new UserStaff(1L, RuoloStaff.MENTORE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setNome(request.getNome());
        segnalazione.setDescrizione(request.getDescrizione());
        segnalazione.setTeamSegnalato(team);
        segnalazione.setMentore(mentore);

        if(hackathon.getSegnalazioni() == null) {
            hackathon.setSegnalazioni(new ArrayList<>());
        }

        hackathon.getSegnalazioni().add(segnalazione);
        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return segnalazione;
    }

    @Override
    public Segnalazione deleteSegnalazione(Long idHackathon, Long idSegnalazione) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) return null;

        Segnalazione toRemove = null;
        for(Segnalazione s : hackathon.getSegnalazioni()) {
            if(s.getId().equals(idSegnalazione)) {
                toRemove = s;
                break;
            }
        }

        if(toRemove == null) return null;

        hackathon.getSegnalazioni().remove(toRemove);
        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return toRemove;
    }

    @Override
    public Segnalazione getSegnalazioneById(Long idHackathon, Long idSegnalazione) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        unitOfWork.saveChanges();
        if(hackathon == null) return null;

        for(Segnalazione s : hackathon.getSegnalazioni()) {
            if(s.getId().equals(idSegnalazione)) {
                return s;
            }
        }

        return null;
    }

    @Override
    public List<Segnalazione> getAllSegnalazioniByHackathon(Long idHackathon) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        unitOfWork.saveChanges();
        if(hackathon == null) return new ArrayList<>();

        return hackathon.getSegnalazioni();
    }
}
