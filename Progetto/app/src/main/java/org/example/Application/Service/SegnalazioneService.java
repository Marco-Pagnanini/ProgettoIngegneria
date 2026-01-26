package org.example.Application.Service;

import org.example.Api.Exception.BadRequestException;
import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Abstraction.Service.ISegnalazioneService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.StatoSegnalazione;
import org.example.Core.models.*;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegnalazioneService implements ISegnalazioneService {
    private IUnitOfWork unitOfWork;
    private Validator<Segnalazione> validator;
    public SegnalazioneService(IUnitOfWork unitOfWork, Validator<Segnalazione> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Segnalazione inviaSegnalazione(Long idHackathon, SegnalazioneRequest request) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }

        Team team = unitOfWork.teamRepository().getById(request.getIdTeamSegnalazione());
        if (team == null) {
            throw new ResourceNotFoundException("Team con id " + request.getIdTeamSegnalazione() + " non trovato");
        }

        UserStaff mentore = unitOfWork.userStaffRepository().getById(request.getIdMentore());

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setNome(request.getNome());
        segnalazione.setDescrizione(request.getDescrizione());
        segnalazione.setTeamSegnalato(team);
        segnalazione.setMentore(mentore);
        segnalazione.setStatoSegnalazione(StatoSegnalazione.APERTA);

        if (!validator.validate(segnalazione)) {
            throw new ValidationException("Dati segnalazione non validi");
        }

        if (hackathon.getTeams() == null || hackathon.getTeams().stream().noneMatch(t -> t.getId().equals(team.getId()))) {
            throw new BadRequestException("Il team non partecipa a questo hackathon");
        }

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
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }

        Segnalazione toRemove = null;
        for(Segnalazione s : hackathon.getSegnalazioni()) {
            if(s.getId().equals(idSegnalazione)) {
                toRemove = s;
                break;
            }
        }

        if(toRemove == null) {
            throw new ResourceNotFoundException("Segnalazione con id " + idSegnalazione + " non trovata");
        }

        hackathon.getSegnalazioni().remove(toRemove);
        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return toRemove;
    }

    @Override
    public Segnalazione getSegnalazioneById(Long idHackathon, Long idSegnalazione) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }
        unitOfWork.saveChanges();

        for(Segnalazione s : hackathon.getSegnalazioni()) {
            if(s.getId().equals(idSegnalazione)) {
                return s;
            }
        }

        return null;
    }

    @Override
    public List<Segnalazione> getAllSegnalazioni() {
        List<Segnalazione> response =  unitOfWork.segnalazioneRepository().getAll();
        unitOfWork.saveChanges();
        return  response;
    }

    @Override
    public List<Segnalazione> visualizzaSegnalazione(Long idHackathon) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }
        unitOfWork.saveChanges();
        return hackathon.getSegnalazioni();
    }
}
