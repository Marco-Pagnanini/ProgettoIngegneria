package org.example.Application.Abstraction.Repository;

import org.example.Core.models.Team;

public interface ITeamRepository extends CrudRepository<Team, Long> {
    boolean existInHackathon(Long idTeam, Long idHackathon);
}
