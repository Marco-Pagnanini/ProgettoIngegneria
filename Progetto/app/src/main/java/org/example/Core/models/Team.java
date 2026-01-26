package org.example.Core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Marco Pagnanini
 */
@Getter
@Setter
@NoArgsConstructor
public class Team {
    private Long id;
    private String nome;
    private User teamLeader;
    private List<User> membriTeam;
    private LocalDate dataCreazione;


}
