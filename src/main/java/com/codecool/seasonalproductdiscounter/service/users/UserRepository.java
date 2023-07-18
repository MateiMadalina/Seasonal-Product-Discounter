package com.codecool.seasonalproductdiscounter.service.users;

import com.codecool.seasonalproductdiscounter.model.users.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    void addUser(User user);
    User get(String username);
    User get(int id);
}
