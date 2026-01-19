package org.example.Api.Models.Request;

import java.util.List;

public class TeamRequest {
    private String teamName;
    private Long idTeamLeader;
    private List<Long> idMembriDelTeam;

    public String getTeamName() {
        return teamName;
    }

    public Long getIdTeamLeader() {
        return idTeamLeader;
    }

    public List<Long> getIdMembriDelTeam() {
        return idMembriDelTeam;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setIdTeamLeader(Long idTeamLeader) {
        this.idTeamLeader = idTeamLeader;
    }

    public void setIdMembriDelTeam(List<Long> idMembriDelTeam) {
        this.idMembriDelTeam = idMembriDelTeam;
    }
}
