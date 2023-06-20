package com.codecool.seasonalproductdiscounter.service.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.List;
import java.util.List;
import java.util.Map;

public interface ProductBrowser {

    List<Product> getAll();
    List<Product> getByName(String name);
    List<Product> getByColor(Color color);
    List<Product> getBySeason(Season season);

    List<Product> getByPriceSmallerThan(double price);
    List<Product> getByPriceGreaterThan(double price);
    List<Product> getByPriceRange(double minimumPrice, double maximumPrice);

    Map<String, List<Product>> groupByName();
    Map<Color, List<Product>> groupByColor();
    Map<Season, List<Product>> groupBySeason();
    Map<PriceRange, List<Product>> groupByPriceRange();

    List<Product> orderByName();
    List<Product> orderByColor();
    List<Product> orderBySeason();
    List<Product> orderByPrice();
}

