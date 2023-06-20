package com.codecool.seasonalproductdiscounter.service.products.provider;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomProductGenerator implements ProductProvider {

    private static final Random RANDOM = new Random();
    private static final Color[] COLORS = Color.values();
    private static final Season[] SEASONS = Season.values();
    private static final String[] NAMES = {"skirt", "T-shirt", "jacket", "shirt", "hat", "gloves"};

    private final List<Product> products;

    public RandomProductGenerator(int count, double minimumPrice, double maximumPrice) {
        products = generateRandomProducts(count, minimumPrice, maximumPrice);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    private List<Product> generateRandomProducts(int count, double minimumPrice, double maximumPrice) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Color color = getRandomColor();
            result.add(new Product(i, getRandomName(color), color, getRandomSeason(), getRandomPrice(minimumPrice, maximumPrice), false));
        }
        return result;
    }

    private Color getRandomColor() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

    private String getRandomName(Color color) {
        return color.toString() + " " + NAMES[RANDOM.nextInt(NAMES.length)];
    }

    private Season getRandomSeason() {
        return SEASONS[RANDOM.nextInt(SEASONS.length)];
    }

    private double getRandomPrice(double minimumPrice, double maximumPrice) {
        return Math.round(RANDOM.nextDouble() * (maximumPrice - minimumPrice) + minimumPrice);
    }
}
