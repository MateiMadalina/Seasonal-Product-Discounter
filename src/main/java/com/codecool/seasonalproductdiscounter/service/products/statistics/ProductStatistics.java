package com.codecool.seasonalproductdiscounter.service.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface ProductStatistics {
    int totalProducts();
    Optional<Product> getMostExpensive();
    Optional<Product> getCheapest();
    OptionalDouble getAveragePrice();

    Map<String, Double> getAveragePricesByName();
    Map<Color, Double> getAveragePricesByColor();
    Map<Season, Double> getAveragePricesBySeason();
    Map<PriceRange, Double> getAveragePricesByPriceRange();

    Map<String, Integer> getCountByName();
    Map<Color, Integer> getCountByColor();
    Map<Season, Integer> getCountBySeason();
    Map<PriceRange, Integer> getCountByPriceRange();
}

