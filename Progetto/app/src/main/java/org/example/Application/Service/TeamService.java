package org.example.Application.Service;

import org.example.Api.Exception.BadRequestException;
import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Request.InvitoRequest;
import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.RuoloUser;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService implements ITeamService {

    private final IUnitOfWork unitOfWork;
    private final Validator<Team> validator;
    private final IInvitoService invitoService;

    public TeamService(IUnitOfWork unitOfWork, Validator<Team> validator, IInvitoService invitoService) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
        this.invitoService = invitoService;
    }


    @Override
    public Team creazioneTeam(TeamRequest request) {
        Team team = new Team();

        User leader = unitOfWork.userRepository().getById(request.getIdTeamLeader());
        if(leader == null) {
            throw new ResourceNotFoundException("Team leader con id " + request.getIdTeamLeader() + " non trovato");
        }

        //TODO InvitoService per inviare l'invito
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

        leader.setRuolo(RuoloUser.TEAM_LEADER);
        team.setNome(request.getTeamName());
        team.setTeamLeader(leader);
        team.setMembriTeam(membriDelTeam);



        if(!validator.validate(team)) {
            throw new ValidationException("Dati team non validi");
        }



        Team createTeam = unitOfWork.teamRepository().create(team);
        leader.setTeam(team);
        unitOfWork.userRepository().update(leader);


        for(Long id : request.getIdMembriDelTeam()) {
            invitoService.creaInvito(new InvitoRequest(team.getId(), id, LocalDate.now()));
        }

        unitOfWork.saveChanges();

        return team;
    }

    /**
     * FIX Team with TeamRequest
     * @param team
     * @return
     */
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
