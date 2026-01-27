package org.example.Application.Abstraction.Repository;

import org.example.Core.models.UserStaff;

import java.util.Optional;

public interface IUserStaffRepository extends CrudRepository<UserStaff, Long> {
    Optional<UserStaff> findByEmail(String email);
}
