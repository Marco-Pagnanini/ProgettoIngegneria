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
        users.add(user);
        return user;
    }

    @Override
    public User delete(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        for (User user1 : users) {
            if (user1.getId().equals(user.getId())) {
                users.remove(user1);
                users.add(user);
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
