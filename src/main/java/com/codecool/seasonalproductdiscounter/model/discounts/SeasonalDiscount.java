package com.codecool.seasonalproductdiscounter.model.discounts;

import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;

public record SeasonalDiscount(String name, int rate, int seasonShift) implements Discount {
    public boolean accepts(Product product, LocalDate date) {
        Season shiftedSeason = product.season().shift(seasonShift);
        return shiftedSeason.contains(date);
    }

    @Override
    public String toString() {
        return "SeasonalDiscount{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", seasonShift=" + seasonShift +
                '}';
    }
}

