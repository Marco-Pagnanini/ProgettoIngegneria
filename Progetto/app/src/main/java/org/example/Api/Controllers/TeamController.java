package org.example.Api.Controllers;

import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Core.models.Team;

public class TeamController {
    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    public Team addTeam(TeamRequest request) {
        return teamService.addTeam(request);

    }
}
