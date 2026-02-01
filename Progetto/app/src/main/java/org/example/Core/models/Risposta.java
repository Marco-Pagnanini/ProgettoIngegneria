package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Risposta {
    /**
     * identificativo univoco della risposta
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testo;

    /**
     * Team che ha inviato la risposta
     * Molte Risposte sono inviate da 1 Team
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * Hackathon in cui è stato inviata la risposta
     * Molte Risposte appartengono a 1 Hackathon
     */
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    /**
     * Sottomissione presa in considerazione per la risposta
     * Molte Risposte rispondono a 1 SottoMissione
     */
    @ManyToOne
    @JoinColumn(name = "sotto_missione_id")
    private SottoMissione sottoMissione;

    /**
     * data di inivio della risposta
     */
    private LocalDateTime dataInvio = LocalDateTime.now();

    /**
     * una rispsota può avere più valutazioni
     * 1 Risposta ha 1 Valutazione
     */
    @OneToOne(mappedBy = "risposta")
    private Valutazione valutazione;

}
