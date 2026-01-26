package org.example.Api.Controllers;

import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Abstraction.Service.ITeamService;
import org.example.Core.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/team")
public class TeamController {
    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody TeamRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Team team = teamService.creazioneTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team);
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        if (team == null || team.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Team updated = teamService.updateTeam(team);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable Long id) {
        Team deleted = teamService.deleteTeam(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}
