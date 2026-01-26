package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValutazioneRequest {
    private Long idRisposta;
    private Integer punteggio;
    private String testo;
}
