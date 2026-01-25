package org.example.Core.models;

import org.example.Core.enums.RuoloStaff;
import org.example.Core.enums.RuoloUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Entità UserStaff:
 * gestisce tutti i MembriDelloStaff
 *
 * @author Marco Pagnanini
 */
public class UserStaff {
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


    public UserStaff(Long id, RuoloStaff ruolo) {
            this.id = id;
            this.ruolo = ruolo;
    }

    public UserStaff(){

    }

        public void setId(Long id) {
                this.id = id;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public void setCognome(String cognome) {
                this.cognome = cognome;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setCellulare(String cellulare) {
                this.cellulare = cellulare;
        }

        public void setDataNascita(LocalDate dataNascita) {
                this.dataNascita = dataNascita;
        }

        public void setDataCreazione(LocalDateTime dataCreazione) {
                this.dataCreazione = dataCreazione;
        }

        public void setRuolo(RuoloStaff ruolo) {
                this.ruolo = ruolo;
        }

        public void setHackathons(List<Hackathon> hackathons) {
                this.hackathons = hackathons;
        }

        public Long getId() {
                return id;
        }

        public String getNome() {
                return nome;
        }

        public String getCognome() {
                return cognome;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

        public String getCellulare() {
                return cellulare;
        }

        public LocalDate getDataNascita() {
                return dataNascita;
        }

        public LocalDateTime getDataCreazione() {
                return dataCreazione;
        }

        public RuoloStaff getRuolo() {
                return ruolo;
        }

        public List<Hackathon> getHackathons() {
                return hackathons;
        }
}
