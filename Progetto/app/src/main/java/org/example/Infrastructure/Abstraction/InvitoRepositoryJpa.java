package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Invito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitoRepositoryJpa extends JpaRepository<Invito, Long> {
}
