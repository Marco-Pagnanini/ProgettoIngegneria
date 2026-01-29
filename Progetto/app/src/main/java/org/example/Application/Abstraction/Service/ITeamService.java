package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.TeamRequest;
import org.example.Core.models.Team;

import java.util.List;

public interface ITeamService {
    Team creazioneTeam(TeamRequest team);
    Team updateTeam(Team team);
    List<Team> getAllTeams();
    Team getTeamById(Long id);
}
