package org.example.Application.Service;

import org.example.Application.Abstraction.Service.IValutazioniService;
import org.example.Core.models.Risposta;
import org.example.Core.models.Valutazione;
import org.example.utils.UnitOfWork.IUnitOfWork;

public class ValutazioneService implements IValutazioniService {
    private IUnitOfWork unitOfWork;

    public ValutazioneService(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }


    @Override
    public Valutazione valutaSottoMissione(Long idRisposta, Integer punteggio, String testo) {
        Risposta risposta = unitOfWork.rispostaRepository().getById(idRisposta);

        Valutazione valutazione = new Valutazione();
        valutazione.setRisposta(risposta);
        valutazione.setPunteggio(punteggio);
        valutazione.setGiudizio(testo);

        valutazione = unitOfWork.valutazioneRepository().create(valutazione);

        unitOfWork.saveChanges();
        return valutazione;


    }
}
