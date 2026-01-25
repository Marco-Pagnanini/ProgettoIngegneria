package org.example.Application.Abstraction.Repository;

import org.example.Core.models.User;

public interface IUserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
