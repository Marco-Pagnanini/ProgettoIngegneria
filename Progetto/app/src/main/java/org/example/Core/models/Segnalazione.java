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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private StatoSegnalazione statoSegnalazione;

    // Molte Segnalazioni riguardano 1 Team
    @ManyToOne
    @JoinColumn(name = "team_segnalato_id")
    private Team teamSegnalato;

    // Molte Segnalazioni sono ricevute da 1 Mentore
    @ManyToOne
    @JoinColumn(name = "mentore_id")
    private UserStaff mentore;

    // Molte Segnalazioni appartengono a 1 Hackathon
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    private LocalDateTime dataCreazione;

}
