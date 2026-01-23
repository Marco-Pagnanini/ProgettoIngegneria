package org.example.Core.models;

public class SottoMissione {
    private Long id;
    private String titolo;
    private String descrizione;
    private Hackathon hackathon;

    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Hackathon getHackathon(){return hackathon;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setHackathon(Hackathon hackathon) { this.hackathon = hackathon; }
}
