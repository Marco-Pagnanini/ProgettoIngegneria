package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoSegnalazione;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class Segnalazione {
    private Long id;
    private String nome;
    private String descrizione;
    private StatoSegnalazione  statoSegnalazione;
    private Team teamSegnalato;
    private UserStaff mentore;
    private Hackathon hackathon;
    private LocalDateTime dataCreazione;

}
