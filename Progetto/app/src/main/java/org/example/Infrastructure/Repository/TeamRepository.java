package org.example.Infrastructure.Repository;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Application.Abstraction.Repository.ITeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements ITeamRepository {
    private List<Team> teams;

    private long nextId = 1L;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }


    @Override
    public Team create(Team team) {
        team.setId(nextId++);
        teams.add(team);
        return team;
    }

    @Override
    public Team delete(Long id) {
        for(Team team : teams) {
            if(team.getId().equals(id)) {
                teams.remove(team);
                return team;
            }
        }
        return null;
    }

    @Override
    public Team update(Team team) {
        for(Team t : teams) {
            if(t.getId().equals(team.getId())) {
                teams.remove(t);
                teams.add(team);
                return team;
            }
        }
        return null;
    }

    @Override
    public Team getById(Long id) {
        for(Team team : teams) {
            if(team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }

    @Override
    public List<Team> getAll() {
        return teams;
    }

    @Override
    public boolean existInHackathon(Long idTeam, Long idHackathon) {
        return false;
    }
}
