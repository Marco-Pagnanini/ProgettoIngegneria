package org.example.Infrastructure.Abstraction;

import org.example.Core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
