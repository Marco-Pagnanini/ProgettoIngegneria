package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Segnalazione;

public class SegnalazioneValidator implements Validator<Segnalazione> {
    private Segnalazione segnalazione;

    @Override
    public boolean validate(Segnalazione segnalazione) {
        if(segnalazione == null) return false;
        if(segnalazione.getDescrizione() == null || segnalazione.getDescrizione().isEmpty()) return false;
        if(segnalazione.getNome() == null || segnalazione.getNome().isEmpty()) return false;
        return true;
    }
}
