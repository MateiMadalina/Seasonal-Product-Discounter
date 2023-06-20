package com.codecool.seasonalproductdiscounter.service.products.repository;

import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAvailableProducts();
    boolean addProducts(List<Product> products);
    boolean setProductAsSold(Product product);
}

