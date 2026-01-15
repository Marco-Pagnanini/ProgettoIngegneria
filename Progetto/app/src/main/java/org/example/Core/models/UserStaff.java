package org.example.Core.models;

import org.example.Core.models.enums.RuoloStaff;
import org.example.Core.models.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserStaff {
    package org.example.Core.models;

import org.example.Core.models.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

    /**
     * Entità User:
     *
     * @author Marco Pagnanini
     */
    public class User {
        /**
         * identificativo univoco per l'utente
         * future implementazioni: usare {@link java.util.UUID} per una maggiore sicurezza
         */
        private Long id;
        /**
         * nome dell'utente
         */
        private String nome;
        /**
         * cognome dell'utente
         */
        private String cognome;
        /**
         * email dell'utente
         * future implementazioni : validatore per l'email
         */
        private String email;
        /**
         * password crittografata dell'utente
         */
        private String password;
        /**
         * numero telefonico dell'utente
         * future implementazioni : possibilità di verificare tramite sms l'utente
         */
        private String cellulare;
        /**
         * data di nascita dell'utente
         */
        private LocalDate dataNascita;
        /**
         * data di quando l'utente crea l'account
         */
        private LocalDateTime dataCreazione;
        /**
         * Ruolo dell'utente
         */
        private RuoloStaff ruolo;
        
        private List<Hackathon> hackathons;




    }

}
