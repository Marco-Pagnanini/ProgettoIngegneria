package org.example.Api.Models.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SegnalazioneResponse {
    private String nome;
    private String descrizione;

    private Long idTeamSegnalazione;
    private String nomeTeamSegnalazione;

    private Long idMentore;
    private String nomeMentore;
}
