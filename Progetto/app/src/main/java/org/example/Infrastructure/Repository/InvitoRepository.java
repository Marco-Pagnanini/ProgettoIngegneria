package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Abstraction.InvitoRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvitoRepository implements IInvitoRepository {
    private final InvitoRepositoryJpa repository;

    public InvitoRepository(InvitoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Invito create(Invito invito) {
        return repository.save(invito);
    }

    @Override
    public Invito delete(Long id) {
        Invito invito = repository.findById(id).orElse(null);
        if (invito != null) {
            repository.delete(invito);
        }
        return invito;
    }

    @Override
    public Invito update(Invito invito) {
        return repository.save(invito);
    }

    @Override
    public Invito getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Invito> getAll() {
        return repository.findAll();
    }
}
