package org.example.Infrastructure.Repository;

import org.example.Core.models.Segnalazione;

import java.util.ArrayList;
import java.util.List;

public class SegnalazioneRepository implements ISegnalazioneRepository{
    private List<Segnalazione> segnalazioni;

    public SegnalazioneRepository() {
        this.segnalazioni =  new ArrayList<>();
    }

    public Segnalazione create(Segnalazione segnalazione) {
        segnalazione.setId(1L);
        this.segnalazioni.add(segnalazione);
        return segnalazione;
    }

    public List<Segnalazione> getAll() {
        return this.segnalazioni;
    }

    public Segnalazione getById(Long id) {
        for (Segnalazione segnalazioni : segnalazioni){
            if (segnalazioni.getId().equals(id)){
                return segnalazioni;
            }
        }
        return null;
    }

    public Segnalazione delete(Long id) {
        for (Segnalazione segnalazioni : segnalazioni){
            if (segnalazioni.getId().equals(id)){
                segnalazioni.remove(segnalazioni);
                return segnalazioni;
            }
        }
        return null;
    }

    public Segnalazione update(Segnalazione segnalazione) {
        for (Segnalazione s : segnalazioni){
            if (s.getId().equals(segnalazione.getId())){
                segnalazioni.remove(s);
                segnalazioni.add(segnalazione);
                return segnalazione;
            }
        }
        return null;
    }
}
