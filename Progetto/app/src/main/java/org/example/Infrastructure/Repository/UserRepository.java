package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IUserRepository;
import org.example.Core.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }


    @Override
    public User create(User user) {
        user.setId(1L);
        users.add(user);
        return user;
    }

    @Override
    public User delete(Long id) {
        for (int idx = 0; idx < users.size(); idx++) {
            User u = users.get(idx);
            if (u.getId().equals(id)) {
                return users.remove(idx);
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        for (int idx = 0; idx < users.size(); idx++) {
            User u = users.get(idx);
            if (u.getId().equals(user.getId())) {
                users.set(idx, user);
                return user;
            }
        }
        return null;
    }


    @Override
    public User getById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
