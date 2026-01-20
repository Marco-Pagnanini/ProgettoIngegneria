package org.example.Application.Service;

import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.RuoloUser;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class TeamService implements ITeamService {

    private final IUnitOfWork unitOfWork;
    private final Validator<Team> validator;

    public TeamService(IUnitOfWork unitOfWork, Validator<Team> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Team addTeam(TeamRequest request) {
        Team team = new Team();

        User leader = unitOfWork.userRepository().getById(request.getIdTeamLeader());
        if(leader == null) return null;

        List<User> membriDelTeam = new ArrayList<>();
        for(Long id : request.getIdMembriDelTeam()){
           membriDelTeam.add(unitOfWork.userRepository().getById(id));
        }
        if(membriDelTeam.isEmpty()) return null;

        team.setNome(request.getTeamName());
        team.setTeamLeader(leader);
        team.setMembriTeam(membriDelTeam);


        if(!validator.validate(team)) return null;

        unitOfWork.teamRepository().create(team);
        unitOfWork.saveChanges();

        return team;
    }

    @Override
    public Team updateTeam(Team team) {
        if(!validator.validate(team)) return null;

        unitOfWork.teamRepository().update(team);
        unitOfWork.saveChanges();
        return team;
    }

    @Override
    public Team deleteTeam(Long id) {
        Team response = unitOfWork.teamRepository().delete(id);
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
