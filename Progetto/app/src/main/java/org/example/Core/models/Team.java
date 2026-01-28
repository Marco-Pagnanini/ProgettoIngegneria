package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco Pagnanini
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private Double saldo = 0.0;

    // 1 Team è composto da 1 TeamLeader
    @ManyToOne
    @JoinColumn(name = "team_leader_id")
    private User teamLeader;

    // 1 Team è composto da Molti MembriDelTeam
    @OneToMany(mappedBy = "team")
    private List<User> membriTeam = new ArrayList<>();

    private LocalDate dataCreazione;

    // 1 Team invia Molti Inviti
    @OneToMany(mappedBy = "dalTeam")
    private List<Invito> inviti = new ArrayList<>();

    // Molti Team partecipano a Molti Hackathon (lato inverso)
    @ManyToMany(mappedBy = "teams")
    private List<Hackathon> hackathons = new ArrayList<>();

}
