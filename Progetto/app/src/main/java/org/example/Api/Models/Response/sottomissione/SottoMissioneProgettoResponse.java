// Response per Progetto
package org.example.Api.Models.Response.sottomissione;
import org.example.Api.Models.Response.SottoMissioneResponse;

public class SottoMissioneProgettoResponse extends SottoMissioneResponse {
    private String urlProgetto;
    private String urlDemo;

    public String getUrlProgetto() {
        return urlProgetto;
    }

    public void setUrlProgetto(String urlProgetto) {
        this.urlProgetto = urlProgetto;
    }

    public String getUrlDemo() {
        return urlDemo;
    }

    public void setUrlDemo(String urlDemo) {
        this.urlDemo = urlDemo;
    }
}