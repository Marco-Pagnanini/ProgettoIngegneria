package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class SottoMissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    private String descrizione;

    // Molte SottoMissioni appartengono a 1 Hackathon
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    // 1 SottoMissione contiene Molte Risposte
    @OneToMany(mappedBy = "sottoMissione")
    private List<Risposta> risposte = new ArrayList<>();

}
