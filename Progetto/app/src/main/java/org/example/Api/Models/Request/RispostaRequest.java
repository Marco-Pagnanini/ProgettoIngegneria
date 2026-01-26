package org.example.Api.Models.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RispostaRequest {
    private Long idRisposta;
    private Long idHackathon;
    private Long idTeam;
    private Long idSottomissione;
    private String risposta;

}
