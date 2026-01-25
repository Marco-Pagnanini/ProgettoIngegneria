package org.example.Application.Service;

import org.example.Application.Abstraction.Service.IValutazioniService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Risposta;
import org.example.Core.models.Valutazione;
import org.example.utils.UnitOfWork.IUnitOfWork;

public class ValutazioneService implements IValutazioniService {
    private final IUnitOfWork unitOfWork;
    private final Validator<Valutazione> validator;

    public ValutazioneService(IUnitOfWork unitOfWork, Validator<Valutazione> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Valutazione valutaSottoMissione(Long idRisposta, Integer punteggio, String testo) {
        Risposta risposta = unitOfWork.rispostaRepository().getById(idRisposta);

        Valutazione valutazione = new Valutazione();
        valutazione.setRisposta(risposta);
        valutazione.setPunteggio(punteggio);
        valutazione.setGiudizio(testo);

        if(!validator.validate(valutazione)) return null;

        Valutazione response = unitOfWork.valutazioneRepository().create(valutazione);

        unitOfWork.saveChanges();
        return response;


    }
}
