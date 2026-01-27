package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.State;
import org.example.Core.models.Team;
import org.example.Core.models.User;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class HackathonRequest {
    private String nome;
    private String regolamento;
    private String argomento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private Double premio;
    private Integer dimensioneMassimaTeam;
    private Integer dimensioneMinimaTeam;
    private Integer numeroMassimoPersone;
    private Integer numeroMinimoPersone;
    private Long idOrganizzatore;
    private Long idGiudice;
    private List<Long> idMentori;


}
