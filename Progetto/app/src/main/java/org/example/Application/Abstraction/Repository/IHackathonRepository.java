package org.example.Application.Abstraction.Repository;

import org.example.Core.models.Hackathon;

import java.util.List;

public interface IHackathonRepository extends CrudRepository<Hackathon, Long> {

    Hackathon create(Hackathon hackathon);
    Hackathon update(Hackathon hackathon);
    Hackathon delete(Long id);
    Hackathon getById(Long id);
    List<Hackathon> getAll();


}
