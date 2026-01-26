package org.example.Application.Service;

import org.example.Api.Exception.BadRequestException;
import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService implements ITeamService {

    private final IUnitOfWork unitOfWork;
    private final Validator<Team> validator;

    public TeamService(IUnitOfWork unitOfWork, Validator<Team> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Team creazioneTeam(TeamRequest request) {
        Team team = new Team();

        User leader = unitOfWork.userRepository().getById(request.getIdTeamLeader());
        if(leader == null) {
            throw new ResourceNotFoundException("Team leader con id " + request.getIdTeamLeader() + " non trovato");
        }

        List<User> membriDelTeam = new ArrayList<>();
        for(Long id : request.getIdMembriDelTeam()) {
            User membro = unitOfWork.userRepository().getById(id);
            if(membro == null) {
                throw new ResourceNotFoundException("Membro con id " + id + " non trovato");
            }
            membriDelTeam.add(membro);
        }
        if(membriDelTeam.isEmpty()) {
            throw new BadRequestException("Il team deve avere almeno un membro");
        }

        team.setNome(request.getTeamName());
        team.setTeamLeader(leader);
        team.setMembriTeam(membriDelTeam);

        if(!validator.validate(team)) {
            throw new ValidationException("Dati team non validi");
        }

        unitOfWork.teamRepository().create(team);
        unitOfWork.saveChanges();

        return team;
    }

    @Override
    public Team updateTeam(Team team) {
        if(!validator.validate(team)) {
            throw new ValidationException("Dati team non validi");
        }
        unitOfWork.teamRepository().update(team);
        unitOfWork.saveChanges();
        return team;
    }

    @Override
    public Team deleteTeam(Long id) {
        Team response = unitOfWork.teamRepository().delete(id);
        if(response == null) {
            throw new ResourceNotFoundException("Team con id " + id + " non trovato");
        }
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> response = unitOfWork.teamRepository().getAll();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Team getTeamById(Long id) {
        Team response = unitOfWork.teamRepository().getById(id);
        unitOfWork.saveChanges();
        return response;
    }
}
