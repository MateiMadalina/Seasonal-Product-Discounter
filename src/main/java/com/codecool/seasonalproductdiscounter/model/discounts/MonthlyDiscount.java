package com.codecool.seasonalproductdiscounter.model.discounts;

import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public record MonthlyDiscount(String name, int rate, Set<Month> months) implements Discount {
    @Override
    public boolean accepts(Product product, LocalDate date) {
        for (Month month : months) {
            if (month.getValue() == date.getMonthValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MonthlyDiscount{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", months=" + months +
                '}';
    }
}

