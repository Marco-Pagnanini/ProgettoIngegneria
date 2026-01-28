package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISupportoRepository;
import org.example.Core.models.Supporto;
import org.example.Infrastructure.Abstraction.SupportRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupportoRepository implements ISupportoRepository {
    private final SupportRepositoryJpa repository;

    public SupportoRepository(SupportRepositoryJpa repository) {
        this.repository = repository;
    }


    @Override
    public Supporto create(Supporto supporto) {
        return repository.save(supporto);
    }

    @Override
    public Supporto delete(Long id) {
        Supporto response = repository.findById(id).orElse(null);
        repository.delete(response);
        return response;
    }

    @Override
    public Supporto update(Supporto supporto) {
        return repository.save(supporto);
    }

    @Override
    public Supporto getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Supporto> getAll() {
        return repository.findAll();
    }
}
