package org.example.Core.models;
public class Valutazione {
    private Long id;
    private Risposta risposta;  // cosa sta valutando
    private Integer punteggio;
    private String giudizio;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRisposta(Risposta risposta) {
        this.risposta = risposta;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    public void setGiudizio(String giudizio) {
        this.giudizio = giudizio;
    }

    public Long getId() {
        return id;
    }

    public Risposta getRisposta() {
        return risposta;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public String getGiudizio() {
        return giudizio;
    }
}