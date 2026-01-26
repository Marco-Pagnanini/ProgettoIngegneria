package org.example.Infrastructure.Abstraction;

import org.example.Application.Abstraction.Repository.CrudRepository;
import org.example.Core.models.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SegnalazioneRepositoryJpa extends JpaRepository<Segnalazione, Long> {
}
