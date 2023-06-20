package com.codecool.seasonalproductdiscounter.service.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;

import java.util.*;

import static java.util.stream.Collectors.*;


public class ProductStatisticsImpl implements ProductStatistics {

    private final ProductBrowser browser;

    public ProductStatisticsImpl(ProductBrowser browser) {
        this.browser = browser;
    }

    @Override
    public int totalProducts() {
        return browser.getAll().size();
    }

    @Override
    public Optional<Product> getMostExpensive() {
        return browser.getAll().stream()
                .max(Comparator.comparingDouble(Product::price));
    }

    @Override
    public Optional<Product> getCheapest() {
        return browser.getAll().stream()
                .min(Comparator.comparingDouble(Product::price));
    }

    @Override
    public OptionalDouble getAveragePrice() {
        return browser.getAll().stream()
                .mapToDouble(Product::price)
                .average();
    }

    @Override
    public Map<String, Double> getAveragePricesByName() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::name, averagingDouble(Product::price)));
    }

    @Override
    public Map<Color, Double> getAveragePricesByColor() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::color, averagingDouble(Product::price)));
    }

    @Override
    public Map<Season, Double> getAveragePricesBySeason() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::season, averagingDouble(Product::price)));
    }

    @Override
    public Map<PriceRange, Double> getAveragePricesByPriceRange() {
        return browser.groupByPriceRange().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, v -> v.getValue().stream()
                        .mapToDouble(Product::price)
                        .average()
                        .orElse(0.0)));
    }

    @Override
    public Map<String, Integer> getCountByName() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::name, summingInt(p -> 1)));
    }

    @Override
    public Map<Color, Integer> getCountByColor() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::color, summingInt(p -> 1)));
    }

    @Override
    public Map<Season, Integer> getCountBySeason() {
        return browser.getAll().stream()
                .collect(groupingBy(Product::season, summingInt(p -> 1)));
    }

    @Override
    public Map<PriceRange, Integer> getCountByPriceRange() {
        return browser.groupByPriceRange().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, g -> g.getValue().size()));
    }
}

