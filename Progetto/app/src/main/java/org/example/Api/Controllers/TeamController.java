package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.TeamMapper;
import org.example.Api.Models.Mapper.UserMapper;
import org.example.Api.Models.Request.TeamRequest;
import org.example.Api.Models.Response.TeamResponse;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/team")
public class TeamController {
    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<String> addTeam(@RequestBody TeamRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Team team = teamService.creazioneTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new String("Team Creato"));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        List<TeamResponse> response = new ArrayList<>();
        for (Team team : teams) {
            response.add(TeamMapper.toResponse(team));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TeamMapper.toResponse(team));
    }


    @PutMapping
    public ResponseEntity<TeamResponse> updateTeam(@RequestBody Team team) {
        if (team == null) {
            return ResponseEntity.badRequest().build();
        }
        Team updated = teamService.updateTeam(team);
        return ResponseEntity.ok(TeamMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamResponse> deleteTeam(@PathVariable Long id) {
        Team deleted = teamService.deleteTeam(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TeamMapper.toResponse(deleted));
    }
}
