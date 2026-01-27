package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IRispostaRepository;
import org.example.Core.models.Risposta;
import org.example.Infrastructure.Abstraction.RispostaRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RispostaRepository implements IRispostaRepository {
    private final RispostaRepositoryJpa repository;

    public RispostaRepository(RispostaRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Risposta create(Risposta risposta) {
        return repository.save(risposta);
    }

    @Override
    public Risposta delete(Long id) {
        Risposta risposta = repository.findById(id).orElse(null);
        if (risposta != null) {
            repository.delete(risposta);
        }
        return risposta;
    }

    @Override
    public Risposta update(Risposta risposta) {
        return repository.save(risposta);
    }

    @Override
    public Risposta getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Risposta> getAll() {
        return repository.findAll();
    }
}
