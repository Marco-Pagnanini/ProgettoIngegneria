package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IValutazioneRepository;
import org.example.Core.models.Valutazione;

import java.util.ArrayList;
import java.util.List;

public class ValutazioneRepository implements IValutazioneRepository {
    private List<Valutazione> valutazioni;

    public ValutazioneRepository() {
        valutazioni = new ArrayList<>();
    }

    @Override
    public Valutazione create(Valutazione valutazione) {
        valutazioni.add(valutazione);
        return valutazione;
    }

    @Override
    public Valutazione delete(Long id) {
        for (Valutazione valutazione : valutazioni) {
            if (valutazione.getId().equals(id)) {
                valutazioni.remove(valutazione);
                return valutazione;
            }
        }
        return null;
    }

    @Override
    public Valutazione update(Valutazione valutazione) {
        for (Valutazione valutazione1 : valutazioni) {
            if (valutazione1.getId().equals(valutazione.getId())) {
                valutazioni.remove(valutazione1);
                valutazioni.add(valutazione);
                return valutazione;
            }
        }
        return null;
    }

    @Override
    public Valutazione getById(Long id) {
        for (Valutazione valutazione : valutazioni) {
            if (valutazione.getId().equals(id)) {
                return valutazione;
            }
        }
        return null;
    }

    @Override
    public List<Valutazione> getAll() {
        return valutazioni;
    }
}
