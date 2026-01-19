package org.example.utils.Builder;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.Core.models.UserStaff;


import java.time.LocalDate;
import java.util.List;

public interface HackathonBuilder {
    HackathonBuilder nome(String nome);
    HackathonBuilder regolamento(String regolamento);
    HackathonBuilder scadenzaIscrizione(LocalDate scadenzaIscrizione);
    HackathonBuilder dataInizio(LocalDate dataInizio);
    HackathonBuilder dataFine(LocalDate dataFine);
    HackathonBuilder luogo(String luogo);
    HackathonBuilder premio(double premio);
    HackathonBuilder dimensioneMassimaTeam(int dimensioneMassimaTeam);
    HackathonBuilder dimensioneMinimaTeam(int dimensioneMinimaTeam);
    HackathonBuilder giudice(UserStaff giudice);
    HackathonBuilder mentori(List<UserStaff> mentori);
    HackathonBuilder organizzatore(UserStaff organizzatore);
    HackathonBuilder teams(List<Team> teams);
    HackathonBuilder argomento(String argomento);
    HackathonBuilder sottomissioni(List<String> sottomissioni);
    HackathonBuilder numeroMassimoPersone(int numeroMassimoPersone);
    HackathonBuilder numeroMinimoPersone(int numeroMinimoPersone);
    Hackathon build();
}