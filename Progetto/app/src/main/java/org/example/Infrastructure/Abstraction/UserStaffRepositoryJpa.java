package org.example.Infrastructure.Abstraction;

import org.example.Core.models.UserStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStaffRepositoryJpa extends JpaRepository<UserStaff, Long> {
    Optional<UserStaff> findByEmail(String email);
}
