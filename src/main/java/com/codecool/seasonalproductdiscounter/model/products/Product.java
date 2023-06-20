package com.codecool.seasonalproductdiscounter.model.products;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;


public record Product(int id, String name, Color color, Season season, double price, boolean sold) {
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", season=" + season +
                ", price=" + price +
                ", sold=" + sold +
                '}';
    }
}

