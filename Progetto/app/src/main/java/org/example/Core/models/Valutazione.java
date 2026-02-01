package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Valutazione {
    /**
     * identificativo univoco della valutazione
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * la risposta riferita alla valutazione
     */
    @OneToOne
    @JoinColumn(name = "risposta_id", unique = true)
    private Risposta risposta;

    /**
     * punteggio per la risposta
     */
    private Integer punteggio;
    /**
     * giudicizio del giudice
     */
    private String giudizio;

}
