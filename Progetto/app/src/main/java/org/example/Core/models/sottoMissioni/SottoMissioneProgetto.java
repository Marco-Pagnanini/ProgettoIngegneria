package org.example.Core.models.sottoMissioni;

public class SottoMissioneProgetto extends SottoMissione{
    private String urlProgetto;

    public String getUrlProgetto() {
        return urlProgetto;
    }

    public void setUrlProgetto(String urlProgetto) {
        this.urlProgetto = urlProgetto;
    }

    @Override
    public String getTipo() {
        return "Domanda Progetto";
    }
}
