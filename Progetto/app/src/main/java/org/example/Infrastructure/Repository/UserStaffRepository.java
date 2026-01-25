package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IUserStaffRepository;
import org.example.Core.models.UserStaff;

import java.util.ArrayList;
import java.util.List;

public class UserStaffRepository implements IUserStaffRepository {

    private final List<UserStaff> userStaffs;

    public UserStaffRepository() {
        userStaffs = new ArrayList<>();
    }

    @Override
    public UserStaff create(UserStaff userStaff) {
        userStaffs.add(userStaff);
        return userStaff;
    }

    @Override
    public UserStaff delete(Long id) {
        for (UserStaff userStaff : userStaffs) {
            if(userStaff.getId().equals(id)) {
                userStaffs.remove(userStaff);
                return userStaff;
            }
        }
        return null;
    }

    @Override
    public UserStaff update(UserStaff userStaff) {
        for (UserStaff userStaff1 : userStaffs) {
            if(userStaff1.getId().equals(userStaff.getId())) {
                userStaffs.remove(userStaff1);
                userStaffs.add(userStaff);
                return userStaff;
            }
        }
        return null;
    }

    @Override
    public UserStaff getById(Long id) {
        for (UserStaff userStaff : userStaffs) {
            if(userStaff.getId().equals(id)) {
                return userStaff;
            }
        }
        return null;
    }

    @Override
    public List<UserStaff> getAll() {
        return userStaffs;
    }

    @Override
    public UserStaff findByEmail(String email) {
        for (UserStaff userStaff : userStaffs) {
            if(userStaff.getEmail().equals(email)) {
                return userStaff;
            }
        }
        return null;
    }
}
