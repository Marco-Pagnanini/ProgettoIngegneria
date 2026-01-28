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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private SupportoState state;
    @ManyToOne
    private UserStaff userStaff;
    @ManyToOne
    private Hackathon hackathon;
    @ManyToOne
    private Team team;

}
