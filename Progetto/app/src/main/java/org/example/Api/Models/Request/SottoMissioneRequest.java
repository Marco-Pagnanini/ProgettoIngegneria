package org.example.Api.Models.Request;

public class SottoMissioneRequest {
    private String titolo;
    private String descrizione;


    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
