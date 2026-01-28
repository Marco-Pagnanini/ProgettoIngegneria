package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Supporto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepositoryJpa extends JpaRepository<Supporto,Long> {

}
