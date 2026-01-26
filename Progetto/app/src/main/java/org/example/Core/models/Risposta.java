package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class Risposta {
    private Long id;
    private String testo;
    private Team team;
    private Hackathon hackathon;
    private SottoMissione sottoMissione;
    private LocalDateTime dataInvio;


}