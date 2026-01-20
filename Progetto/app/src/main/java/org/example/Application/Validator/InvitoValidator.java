package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Invito;

public class InvitoValidator implements Validator<Invito> {
    private Invito  invito;

    @Override
    public boolean validate(Invito entity) {
        return true;
    }
}
