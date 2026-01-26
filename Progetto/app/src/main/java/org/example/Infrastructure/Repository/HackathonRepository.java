package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Core.models.Hackathon;
import org.example.Infrastructure.Abstraction.HackathonRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HackathonRepository implements IHackathonRepository {
    private final HackathonRepositoryJpa repository;

    public HackathonRepository(HackathonRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Hackathon create(Hackathon hackathon) {
        return repository.save(hackathon);
    }

    @Override
    public Hackathon update(Hackathon hackathon) {
        return repository.save(hackathon);
    }

    @Override
    public Hackathon delete(Long id) {
        Hackathon hackathon = repository.findById(id).orElse(null);
        repository.delete(hackathon);
        return hackathon;
    }



    @Override
    public Hackathon getById(Long id) {
       return repository.findById(id).orElse(null);
    }

    @Override
    public List<Hackathon> getAll() {
        return repository.findAll();
    }
}
