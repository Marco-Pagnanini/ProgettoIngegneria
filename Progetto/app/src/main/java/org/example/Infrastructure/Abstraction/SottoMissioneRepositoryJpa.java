package org.example.Infrastructure.Abstraction;

import org.example.Core.models.SottoMissione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SottoMissioneRepositoryJpa extends JpaRepository<SottoMissione, Long> {
}
