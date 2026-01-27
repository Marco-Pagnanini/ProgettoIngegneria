package org.example.Application.Abstraction.Repository;

import org.example.Core.models.User;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
