package org.example.Infrastructure.Abstraction;

import org.example.Core.models.UserStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStaffRepositoryJpa extends JpaRepository<UserStaff, Long> {
    UserStaff findByEmail(String email);
}
