package com.codecool.seasonalproductdiscounter.ui.selector;

import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.UiFactoryBase;

import java.util.List;
import java.util.Scanner;

public class UiSelector {
    private final List<UiFactoryBase> factories;

    public UiSelector(List<UiFactoryBase> factories) {
        this.factories = factories;
    }

    public UiBase select() {
        System.out.println("Welcome to Seasonal Product Discounter v3");
        displayMenu();
        int input = 0;
        while (input < 1 || input > factories.size()) {
            System.out.println("Please select a screen to show from the list.");
            input = getIntInput();
        }
        return factories.get(input - 1).create();
    }

    private void displayMenu() {
        System.out.println("Available screens:");
        for (int i = 0; i < factories.size(); i++) {
            System.out.println((i + 1) + " - " + factories.get(i).getUiName());
        }
    }

    private static int getIntInput() {
        int input = 0;
        while (input == 0) {
            try {
                input = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number!");
            }
        }
        return input;
    }
}

