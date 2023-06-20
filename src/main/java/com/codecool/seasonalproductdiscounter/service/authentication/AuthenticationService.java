package com.codecool.seasonalproductdiscounter.service.authentication;

import com.codecool.seasonalproductdiscounter.model.users.User;

public interface AuthenticationService {
    boolean authenticate(User user);
}

