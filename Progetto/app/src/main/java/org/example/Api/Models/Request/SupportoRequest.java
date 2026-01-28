package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupportoRequest {
    private Long idMentore;
    private Long idHackathon;
    private Long idTeam;
}
