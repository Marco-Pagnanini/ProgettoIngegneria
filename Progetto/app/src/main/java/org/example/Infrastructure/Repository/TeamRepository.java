package org.example.Infrastructure.Repository;

import org.example.Core.models.Team;
import org.example.Application.Abstraction.Repository.ITeamRepository;
import org.example.Infrastructure.Abstraction.TeamRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository implements ITeamRepository {
    private final TeamRepositoryJpa repository;

    public TeamRepository(TeamRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Team create(Team team) {
        return repository.save(team);
    }

    @Override
    public Team delete(Long id) {
        Team team = repository.findById(id).orElse(null);
        if (team != null) {
            repository.delete(team);
        }
        return team;
    }

    @Override
    public Team update(Team team) {
        return repository.save(team);
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
        // TODO: implementare con query JPA
        return false;
    }
}
