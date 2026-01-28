package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.SupportoState;

@Getter
@Setter
@NoArgsConstructor
public class Supporto {
    private Long id;
    private SupportoState state;
    private UserStaff userStaff;
    private Hackathon hackathon;
    private Team team;

}
