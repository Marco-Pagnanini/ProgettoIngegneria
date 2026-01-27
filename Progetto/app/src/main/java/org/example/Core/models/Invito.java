package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.StatoInvito;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    private String descrizione;

    // Molti Inviti sono inviati da 1 Team
    @ManyToOne
    @JoinColumn(name = "dal_team_id")
    private Team dalTeam;

    // Molti Inviti sono ricevuti da 1 User
    @ManyToOne
    @JoinColumn(name = "per_utente_id")
    private User perUtente;

    @Enumerated(EnumType.STRING)
    private StatoInvito stato;

    private LocalDate dataInvito;

}
