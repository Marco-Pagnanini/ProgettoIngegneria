package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISegnalazioneRepository;
import org.example.Core.models.Segnalazione;
import org.example.Infrastructure.Abstraction.SegnalazioneRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SegnalazioneRepository implements ISegnalazioneRepository {
    private final SegnalazioneRepositoryJpa repository;

    public SegnalazioneRepository(SegnalazioneRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Segnalazione create(Segnalazione segnalazione) {
        return repository.save(segnalazione);
    }

    @Override
    public Segnalazione delete(Long id) {
        Segnalazione segnalazione = repository.findById(id).orElse(null);
        if (segnalazione != null) {
            repository.delete(segnalazione);
        }
        return segnalazione;
    }

    @Override
    public Segnalazione update(Segnalazione segnalazione) {
        return repository.save(segnalazione);
    }

    @Override
    public Segnalazione getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Segnalazione> getAll() {
        return repository.findAll();
    }
}
