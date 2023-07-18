package com.codecool.seasonalproductdiscounter.service.products.repository;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.persistence.SqliteConnector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{

    private final SqliteConnector sqliteConnector;
    private final Logger logger;

    public ProductRepositoryImpl(SqliteConnector sqliteConnector, Logger logger) {
        this.sqliteConnector = sqliteConnector;
        this.logger = logger;
    }

    @Override
    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE sold = ?";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBoolean(1, false);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String colorString = resultSet.getString("color");
                String seasonString = resultSet.getString("season");
                Double price = resultSet.getDouble("price");
                Boolean sold = resultSet.getBoolean("sold");

                Color color = Color.valueOf(colorString);
                Season season = Season.valueOf(seasonString);

                Product product = new Product(productId, productName,color,season,price,sold);

                availableProducts.add(product);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return availableProducts;
    }


    @Override
    public boolean addProducts(List<Product> products) {
        String sql = "INSERT INTO products (id, name, color, season, price, sold) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            for (Product product : products) {
                statement.setInt(1, product.id());
                statement.setString(2, product.name());
                statement.setString(3, product.color().name());
                statement.setString(4, product.season().name());
                statement.setDouble(5, product.price());
                statement.setInt(6, product.sold() ? 1 : 0);

                //statement.executeUpdate();
                statement.addBatch();
            }

            int[] updateCounts = statement.executeBatch();
            for (int count : updateCounts) {
                if (count <= 0) {
                    return false;
                }
            }

            logger.logInfo("Added " + products.size() + " products to the database");
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean setProductAsSold(Product product) {
        String sql = "UPDATE products SET sold = 1 WHERE id = ?";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
             statement.setInt(1, product.id());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return false;
    }



}
