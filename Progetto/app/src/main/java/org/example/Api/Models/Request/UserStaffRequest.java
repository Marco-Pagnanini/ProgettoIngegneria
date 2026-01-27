package org.example.Api.Models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.RuoloStaff;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class UserStaffRequest {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String cellulare;
    private LocalDate dataDiNascita;
    private LocalDate dataCreazione;
    private RuoloStaff ruolo;
}
