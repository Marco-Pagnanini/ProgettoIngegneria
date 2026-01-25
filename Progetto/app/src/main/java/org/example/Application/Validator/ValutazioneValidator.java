package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Valutazione;

public class ValutazioneValidator implements Validator<Valutazione> {

    @Override
    public boolean validate(Valutazione entity) {
        if(entity == null) return false;
        if(entity.getGiudizio() == null || entity.getGiudizio().isEmpty()) return false;
        if(entity.getPunteggio() < 0) return false;
        return true;
    }
}
