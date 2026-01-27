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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Molte Valutazioni si riferiscono a 1 Risposta
    @ManyToOne
    @JoinColumn(name = "risposta_id")
    private Risposta risposta;

    private Integer punteggio;
    private String giudizio;

}
