package org.example.Application.Validator;

import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;

public class HackathonValidator implements Validator<Hackathon> {
    private Hackathon hackathon;

    public HackathonValidator() {
    }

    public boolean validate(Hackathon hackathon){

        if(hackathon == null) return false;
        if(hackathon.getDataFine().isBefore(hackathon.getDataInizio())) return false;
        if(hackathon.getNumeroMassimoPersone() < hackathon.getNumeroMinimoPersone()) return false;
        if(hackathon.getDimensioneMassimaTeam() < hackathon.getDimensioneMassimaTeam()) return false;
        if(hackathon.getPremio() < 0) return false;

        return true;

    }

}
