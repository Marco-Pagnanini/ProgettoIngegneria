package org.example.Api.Models.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoInvito;


@Getter
@Setter
@NoArgsConstructor
public class InvitoResponse {
    private String perUtente;
    private String nomeTeam;
    private StatoInvito statoInvito;
}
