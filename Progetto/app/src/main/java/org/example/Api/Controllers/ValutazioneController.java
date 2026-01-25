package org.example.Api.Controllers;

import org.example.Application.Abstraction.Service.IValutazioniService;
import org.example.Core.models.Valutazione;

public class ValutazioneController {
    private final IValutazioniService valutazioniService;

    public ValutazioneController(IValutazioniService valutazioniService) {
        this.valutazioniService = valutazioniService;
    }

    public Valutazione valutaSottoMissione(Long idRisposta, Integer punteggio, String testo){
        return valutazioniService.valutaSottoMissione(idRisposta, punteggio, testo);
    }
}
