package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IValutazioneRepository;
import org.example.Core.models.Valutazione;
import org.example.Infrastructure.Abstraction.ValutazioneRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ValutazioneRepository implements IValutazioneRepository {
    private final ValutazioneRepositoryJpa repository;

    public ValutazioneRepository(ValutazioneRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Valutazione create(Valutazione valutazione) {
        return repository.save(valutazione);
    }

    @Override
    public Valutazione delete(Long id) {
        Valutazione valutazione = repository.findById(id).orElse(null);
        if (valutazione != null) {
            repository.delete(valutazione);
        }
        return valutazione;
    }

    @Override
    public Valutazione update(Valutazione valutazione) {
        return repository.save(valutazione);
    }

    @Override
    public Valutazione getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Valutazione> getAll() {
        return repository.findAll();
    }
}
