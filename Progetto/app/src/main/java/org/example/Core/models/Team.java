package org.example.Core.models;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Marco Pagnanini
 */
public class Team {
    private Long id;
    private String nome;
    private User teamLeader;
    private List<User> membriTeam;
    private LocalDate dataCreazione;
}
