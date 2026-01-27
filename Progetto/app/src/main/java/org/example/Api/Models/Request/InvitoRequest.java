package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Team;
import org.example.Core.models.User;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InvitoRequest {
    private Long idDelTeam;
    private Long idUtente;
    private LocalDate dataInvito;


}
