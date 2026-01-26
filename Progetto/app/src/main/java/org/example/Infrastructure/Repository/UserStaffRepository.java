package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IUserStaffRepository;
import org.example.Core.models.UserStaff;
import org.example.Infrastructure.Abstraction.UserStaffRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStaffRepository implements IUserStaffRepository {
    private final UserStaffRepositoryJpa repository;

    public UserStaffRepository(UserStaffRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public UserStaff create(UserStaff userStaff) {
        return repository.save(userStaff);
    }

    @Override
    public UserStaff delete(Long id) {
        UserStaff userStaff = repository.findById(id).orElse(null);
        if (userStaff != null) {
            repository.delete(userStaff);
        }
        return userStaff;
    }

    @Override
    public UserStaff update(UserStaff userStaff) {
        return repository.save(userStaff);
    }

    @Override
    public UserStaff getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UserStaff> getAll() {
        return repository.findAll();
    }

    @Override
    public UserStaff findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
