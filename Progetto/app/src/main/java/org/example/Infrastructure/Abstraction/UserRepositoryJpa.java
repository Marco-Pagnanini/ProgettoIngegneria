package org.example.Infrastructure.Abstraction;

import org.example.Core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
