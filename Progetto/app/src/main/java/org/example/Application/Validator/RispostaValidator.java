package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Risposta;

public class RispostaValidator implements Validator<Risposta> {
    @Override
    public boolean validate(Risposta entity) {
        if (entity == null) return false;
        if (entity.getTesto() == null || entity.getTesto().isBlank()) return  false;

        if (entity.getHackathon() == null) return false;
        if (entity.getTeam() == null) return false;
        if (entity.getSottoMissione() == null) return false;

        return true;
    }
}
