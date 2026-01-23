package org.example.Api.Models.Request.sottomissione;

public class SottoMissioneProgettoRequest extends SottoMissioneRequest {

    private String urlProgetto;

    @Override
    public String getTipoSottomissione() {
        return "PROGETTO";
    }

    // Getters e Setters
    public String getUrlProgetto() {
        return urlProgetto;
    }

    public void setUrlProgetto(String urlProgetto) {
        this.urlProgetto = urlProgetto;
    }
}