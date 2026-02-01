package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoSegnalazione;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Segnalazione {
    /**
     * identificativo univoco della segnalazione
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * nome della segnalazione
     */
    private String nome;
    /**
     * descrizione della segnalazione
     */
    private String descrizione;

    /**
     * stato della segnalazione per indicare se è gestita
     */
    @Enumerated(EnumType.STRING)
    private StatoSegnalazione statoSegnalazione;

    /**
     * team segnalato del mentore
     * Molte Segnalazioni riguardano 1 Team
     */
    @ManyToOne
    @JoinColumn(name = "team_segnalato_id")
    private Team teamSegnalato;

    /**
     * mentore che ha inviato la segnalazione
     * Molte Segnalazioni sono ricevute da 1 Mentore
     */
    @ManyToOne
    @JoinColumn(name = "mentore_id")
    private UserStaff mentore;

    /**
     * hackathon relativo della segnalazione
     * Molte Segnalazioni appartengono a 1 Hackathon
     */
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    /**
     * data dell'invio dela segnalazione
     */
    private LocalDateTime dataCreazione = LocalDateTime.now();

}
