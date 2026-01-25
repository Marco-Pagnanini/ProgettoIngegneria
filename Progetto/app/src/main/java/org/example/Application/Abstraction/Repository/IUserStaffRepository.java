package org.example.Application.Abstraction.Repository;

import org.example.Core.models.UserStaff;

public interface IUserStaffRepository extends CrudRepository<UserStaff, Long> {
    public UserStaff findByEmail(String email);
}
