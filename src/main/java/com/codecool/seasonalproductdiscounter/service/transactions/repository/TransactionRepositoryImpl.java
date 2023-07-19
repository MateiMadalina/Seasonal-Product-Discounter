package com.codecool.seasonalproductdiscounter.service.transactions.repository;

import com.codecool.seasonalproductdiscounter.model.transactions.Transaction;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.persistence.SqliteConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            statement.setInt(2, transaction.product().id());
            statement.setDouble(2, transaction.pricePaid());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }
}
