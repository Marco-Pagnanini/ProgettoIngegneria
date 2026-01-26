package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;

@Getter
@Setter
@NoArgsConstructor
public class SegnalazioneRequest {
    private String nome;
    private String descrizione;
    private Long idTeamSegnalazione;
    private Long idMentore;


}
