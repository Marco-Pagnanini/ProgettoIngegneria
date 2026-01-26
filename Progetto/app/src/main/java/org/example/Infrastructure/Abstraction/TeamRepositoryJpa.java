package org.example.Infrastructure.Abstraction;

import org.example.Core.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepositoryJpa extends JpaRepository<Team,Long> {

}
