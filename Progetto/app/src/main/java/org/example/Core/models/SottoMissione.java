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
    /**
     * identificativo univoco della sottomissione
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * titolo della sottomisisone relativa
     */
    private String titolo;
    /**
     * descrizione della sottomissione
     */
    private String descrizione;

    /**
     * hackathon a cui si riferisce la sottomisisone
     * Molte SottoMissioni appartengono a 1 Hackathon
     */
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    /**
     * insieme delle rispsota dei team partecipanti
     * 1 SottoMissione contiene Molte Risposte
     */
    @OneToMany(mappedBy = "sottoMissione")
    private List<Risposta> risposte = new ArrayList<>();

}
