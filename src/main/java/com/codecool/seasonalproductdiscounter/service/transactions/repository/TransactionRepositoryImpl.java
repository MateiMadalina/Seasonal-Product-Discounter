package com.codecool.seasonalproductdiscounter.service.transactions.repository;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.model.transactions.Transaction;
import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.persistence.SqliteConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository{

    private final Logger logger;
    private final SqliteConnector sqliteConnector;

    public TransactionRepositoryImpl(Logger logger, SqliteConnector sqliteConnector) {
        this.logger = logger;
        this.sqliteConnector = sqliteConnector;
    }

    @Override
    public boolean add(Transaction transaction) {
        String sql = "INSERT INTO transactions(date, user_id, product_id, price_paid) VALUES(?, ?, ?, ?)";

        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(transaction.date()));
            statement.setInt(2, transaction.user().id());
            statement.setInt(3, transaction.product().id());
            statement.setDouble(4, transaction.pricePaid());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactions.id AS transaction_id, date, price_paid, " +
                "user_id, users.name AS user_name, users.password AS password, " +
                "product_id, products.name AS product_name, products.color AS product_color, " +
                "products.season AS product_season, products.price AS product_price, products.sold AS sold " +
                "FROM transactions " +
                "INNER JOIN products ON products.id = transactions.product_id " +
                "INNER JOIN users ON users.id = transactions.user_id;";
        ;

        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String colorString = resultSet.getString("product_color");
                String seasonString = resultSet.getString("product_season");
                Double price = resultSet.getDouble("product_price");
                Boolean sold = resultSet.getBoolean("sold");
                Color color = Color.valueOf(colorString);
                Season season = Season.valueOf(seasonString);
                Product product = new Product(productId, productName,color,season,price,sold);

                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                User user = new User(userId,name,password);

                int transactionID = resultSet.getInt("transaction_id");
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                int finalPrice = resultSet.getInt("price_paid");

                Transaction transaction = new Transaction(transactionID, date, user,product,finalPrice);
                transactions.add(transaction);
            }


        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return transactions;
    }
}
