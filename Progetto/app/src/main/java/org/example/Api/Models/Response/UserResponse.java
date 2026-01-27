package org.example.Api.Models.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO di risposta per l'entità User
 * Non include dati sensibili come la password
 *
 * @author Marco Pagnanini
 */
@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String nome;
    private String cognome;
    private String email;
    private String cellulare;
    private LocalDate dataNascita;
    private LocalDateTime dataCreazione;
    private RuoloUser ruolo;

    // Dati del team se presente
    private Long teamId;
    private String teamNome = "-";


}
