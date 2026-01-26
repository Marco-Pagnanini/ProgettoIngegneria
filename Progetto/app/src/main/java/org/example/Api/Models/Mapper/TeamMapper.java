package org.example.Api.Models.Mapper;

import org.example.Api.Models.Response.TeamResponse;
import org.example.Core.models.Team;

public class TeamMapper {

    public static TeamResponse toResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setTeamName(team.getNome());
        teamResponse.setNomeLeader(team.getTeamLeader().getNome());
        return teamResponse;
    }
}
