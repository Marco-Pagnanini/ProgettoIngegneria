package org.example.Application.Abstraction.Repository;

import org.example.Core.models.Hackathon;

import java.util.List;

public interface CrudRepository<T,S> {
    Hackathon create(T t);
    T delete(S id);
    Hackathon update(T t);
    T getById(S id);
    List<T> getAll();
}
