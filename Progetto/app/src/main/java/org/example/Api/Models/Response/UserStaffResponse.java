package org.example.Api.Models.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.RuoloStaff;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Segnalazione;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserStaffResponse {
    private String nome;
    private String cognome;
    private String email;
    private String cellulare;
    private LocalDate dataNascita;
    private LocalDateTime dataCreazione;
    private RuoloStaff ruolo;
    private List<Hackathon> hackathonOrganizzati;
    private List<Hackathon> hackathonValutati;
    private List<Hackathon> hackathonSupportati;

}
