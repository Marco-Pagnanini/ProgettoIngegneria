package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValutazioneRepositoryJpa extends JpaRepository<Valutazione, Long> {
}
