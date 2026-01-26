package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Risposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testo;

    // Molte Risposte sono inviate da 1 Team
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Molte Risposte appartengono a 1 Hackathon
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    // Molte Risposte rispondono a 1 SottoMissione
    @ManyToOne
    @JoinColumn(name = "sotto_missione_id")
    private SottoMissione sottoMissione;

    private LocalDateTime dataInvio;

    // 1 Risposta ha Molte Valutazioni
    @OneToMany(mappedBy = "risposta")
    private List<Valutazione> valutazioni = new ArrayList<>();

}
