package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Segnalazione;

public class SegnalazioneValidator implements Validator<Segnalazione> {

    @Override
    public boolean validate(Segnalazione segnalazione) {
        if(segnalazione == null) return false;
        if(segnalazione.getDescrizione() == null || segnalazione.getDescrizione().isBlank()) return false;
        if(segnalazione.getNome() == null || segnalazione.getNome().isBlank()) return false;
        return true;
    }
}
