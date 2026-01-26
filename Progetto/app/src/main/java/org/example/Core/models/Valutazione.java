package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Valutazione {
    private Long id;
    private Risposta risposta;  // cosa sta valutando
    private Integer punteggio;
    private String giudizio;

}