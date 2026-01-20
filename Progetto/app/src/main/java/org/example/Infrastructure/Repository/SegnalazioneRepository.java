package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISegnalazioneRepository;
import org.example.Core.models.Segnalazione;

import java.util.ArrayList;
import java.util.List;

public class SegnalazioneRepository implements ISegnalazioneRepository {
    private List<Segnalazione> segnalazione;

    public SegnalazioneRepository(){
        this.segnalazione=new ArrayList<>();
    }

    @Override
    public Segnalazione create(Segnalazione segnalazione) {
        return null;
    }

    @Override
    public Segnalazione delete(Long id) {
        return null;
    }

    @Override
    public Segnalazione update(Segnalazione segnalazione) {
        return null;
    }

    @Override
    public Segnalazione getById(Long id) {
        return null;
    }

    @Override
    public List<Segnalazione> getAll() {
        return List.of();
    }
}
