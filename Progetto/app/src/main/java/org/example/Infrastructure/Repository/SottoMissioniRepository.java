package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISottoMissioneRepository;
import org.example.Core.models.SottoMissione;
import org.example.Infrastructure.Abstraction.SottoMissioneRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SottoMissioniRepository implements ISottoMissioneRepository {
    private final SottoMissioneRepositoryJpa repository;

    public SottoMissioniRepository(SottoMissioneRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public SottoMissione create(SottoMissione sottoMissione) {
        return repository.save(sottoMissione);
    }

    @Override
    public SottoMissione delete(Long id) {
        SottoMissione sottoMissione = repository.findById(id).orElse(null);
        if (sottoMissione != null) {
            repository.delete(sottoMissione);
        }
        return sottoMissione;
    }

    @Override
    public SottoMissione update(SottoMissione sottoMissione) {
        return repository.save(sottoMissione);
    }

    @Override
    public SottoMissione getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SottoMissione> getAll() {
        return repository.findAll();
    }
}
