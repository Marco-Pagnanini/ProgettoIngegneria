package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoInvito;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Invito {
    private Long id;
    private String titolo;
    private String descrizione;
    private Team dalTeam;
    private User perUtente;
    private StatoInvito stato;
    private LocalDate dataInvito;


}
