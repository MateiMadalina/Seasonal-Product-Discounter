package com.codecool.seasonalproductdiscounter.ui.authentication;

import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.authentication.AuthenticationService;

import java.util.Scanner;

public class UserAuthenticator {
    private final AuthenticationService authenticationService;
    private final Scanner scanner;

    public UserAuthenticator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        scanner = new Scanner(System.in);
    }

    private User getUser() {
        String userName = getTextInput("Please enter your username: ");
        String password = getTextInput("Please enter your password: ");

//        return new User(userName, password);
        return null;
    }

    private String getTextInput(String text) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(text);
            input = scanner.nextLine().trim();
        }

        return input;
    }

    public boolean authenticate() {
        User user = getUser();
        if (!authenticationService.authenticate(user)) {
            System.out.println("Invalid user");
            return false;
        }

        return true;
    }
}

