package com.codecool.seasonalproductdiscounter.model.transactions;

import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.model.users.User;

import java.time.LocalDate;

public record Transaction(int id, LocalDate date, User user, Product product, double pricePaid) {}

