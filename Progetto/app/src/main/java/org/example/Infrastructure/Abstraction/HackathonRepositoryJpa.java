package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackathonRepositoryJpa extends JpaRepository<Hackathon,Long> {
}
