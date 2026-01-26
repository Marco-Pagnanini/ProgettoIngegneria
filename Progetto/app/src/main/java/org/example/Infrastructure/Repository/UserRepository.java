package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IUserRepository;
import org.example.Core.models.User;
import org.example.Infrastructure.Abstraction.UserRepositoryJpa;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final UserRepositoryJpa repository;

    public UserRepository(UserRepositoryJpa repository) {
        this.repository = repository;

    }


    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = repository.findById(id).orElse(null);
        repository.delete(user);
        return user;
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }


    @Override
    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
