package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Team;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.List;

public class TeamValidator implements Validator<Team> {

    private final IUnitOfWork unitOfWork;

    public TeamValidator(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public boolean validate(Team entity) {
        if(entity == null) return false;
        if(entity.getNome() == null || entity.getNome().trim().isEmpty()) return false;
        if(entity.getTeamLeader() == null) return false;
        if(entity.getMembriTeam() == null || entity.getMembriTeam().isEmpty()) return false;

        List<Team> teams = unitOfWork.teamRepository().getAll();
        for(Team team : teams) {
            if(team.getNome().equalsIgnoreCase(entity.getNome()) &&
                    !team.getId().equals(entity.getId())) {
                return false;
            }
        }

        return true;
    }
}