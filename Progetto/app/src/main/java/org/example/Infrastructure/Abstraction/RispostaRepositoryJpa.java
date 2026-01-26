package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Risposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RispostaRepositoryJpa extends JpaRepository<Risposta, Long> {
}
