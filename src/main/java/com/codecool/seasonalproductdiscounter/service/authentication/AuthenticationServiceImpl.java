package com.codecool.seasonalproductdiscounter.service.authentication;

import com.codecool.seasonalproductdiscounter.model.users.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final Map<String, String> userNamesToPasswords = new HashMap<>() {{
        put("user1", "1234");
        put("user2", "4567");
        put("admin", "admin");
    }};

    public boolean authenticate(User user) {
        return userNamesToPasswords.entrySet()
                .stream()
                .anyMatch(entry -> entry.getKey().equals(user.userName())
                        && entry.getValue().equals(user.password()));
    }
}

