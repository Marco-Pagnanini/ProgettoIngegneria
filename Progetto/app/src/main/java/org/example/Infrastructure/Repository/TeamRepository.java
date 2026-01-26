package org.example.Infrastructure.Repository;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Application.Abstraction.Repository.ITeamRepository;
import org.example.Infrastructure.Abstraction.HackathonRepositoryJpa;
import org.example.Infrastructure.Abstraction.TeamRepositoryJpa;

import java.util.List;

public class TeamRepository implements ITeamRepository {

    private final TeamRepositoryJpa repository;
    private final HackathonRepositoryJpa hackathonRepositoryJpa;

    public TeamRepository(TeamRepositoryJpa repository,  HackathonRepositoryJpa hackathonRepositoryJpa) {
        this.repository = repository;
        this.hackathonRepositoryJpa = hackathonRepositoryJpa;
    }

    @Override
    public Team create(Team team) {
        return repository.save(team);
    }

    @Override
    public Team delete(Long id) {
        Team team = repository.findById(id).orElse(null);
        repository.delete(team);
        return team;
    }

    @Override
    public Team update(Team team) {
        return  repository.save(team);
    }


    @Override
    public Team getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Team> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean existInHackathon(Long idTeam, Long idHackathon) {
        Team team  = repository.findById(idTeam).orElse(null);
        Hackathon hackathon =  hackathonRepositoryJpa.findById(idHackathon).orElse(null);
        return hackathon.getTeams().contains(team);
    }
}
