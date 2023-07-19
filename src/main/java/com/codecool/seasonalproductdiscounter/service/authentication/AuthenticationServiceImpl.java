package com.codecool.seasonalproductdiscounter.service.authentication;

import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.users.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final Map<String, String> userNamesToPasswords = new HashMap<String, String>();

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(User user) {
        List<User> users = userRepository.getUsers();
        for(User u: users){
            userNamesToPasswords.put(u.userName(), u.password());
        }
        return userNamesToPasswords.entrySet()
                .stream()
                .anyMatch(entry -> entry.getKey().equals(user.userName())
                        && entry.getValue().equals(user.password()));
    }
}

