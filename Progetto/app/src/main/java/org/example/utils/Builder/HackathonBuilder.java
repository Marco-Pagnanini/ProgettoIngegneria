package org.example.utils.Builder;
import org.example.Core.models.*;


import java.time.LocalDate;
import java.util.List;

/**
 * Design Pattern: il Builder è un design pattern per creare un hackathon in maniera sequenziale evitando dei costruttori pieni di attributi
 * ma l'hackathon verrà costrutito pezzo per pezzo
 */
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
    HackathonBuilder sottomissioni(List<SottoMissione> sottomissioni);
    HackathonBuilder numeroMassimoPersone(int numeroMassimoPersone);
    HackathonBuilder numeroMinimoPersone(int numeroMinimoPersone);
    Hackathon build();
    Hackathon reset();
}