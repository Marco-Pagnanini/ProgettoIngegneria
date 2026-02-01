package org.example.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.SupportoState;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Supporto {
    /**
     * identificativo univoco del supporto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * stato del supporto (per gestire se è gestito)
     */
    private SupportoState state;
    /**
     * mentore relativo al supporto
     */
    @ManyToOne
    private UserStaff userStaff;
    /**
     * hackathon in cui i team partecipano all'hackathon
     */
    @ManyToOne
    private Hackathon hackathon;
    /**
     * team che ha richiesto il supporto
     */
    @ManyToOne
    private Team team;

}
