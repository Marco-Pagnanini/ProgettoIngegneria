package org.example.Application.Abstraction.Service;

import org.example.Core.models.Valutazione;

public interface IValutazioniService {
    Valutazione valutaSottoMissione(Long idRisposta,Integer punteggio, String testo);

}
