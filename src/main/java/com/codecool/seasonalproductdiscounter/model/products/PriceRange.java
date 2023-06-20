package com.codecool.seasonalproductdiscounter.model.products;

public record PriceRange(double minimum, double maximum) {
    public boolean contains(double price) {
        return price > minimum && price < maximum;
    }
}