package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.SottoMissione;

public class SottoMissioniValidator implements Validator<SottoMissione> {

    @Override
    public boolean validate(SottoMissione entity) {
        if(entity == null) return false;
        if(entity.getTitolo().isEmpty()) return false;
        if(entity.getDescrizione().isEmpty()) return false;

        return true;
    }
}
