package com.codecool.seasonalproductdiscounter.service.users;

import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.persistence.SqliteConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private final SqliteConnector sqliteConnector;
    private final Logger logger;

    public UserRepositoryImpl(SqliteConnector sqliteConnector, Logger logger) {
        this.sqliteConnector = sqliteConnector;
        this.logger = logger;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");

                User user = new User(id,name,password);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users(name, password) VALUES(?, ?)";

        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.userName());
            statement.setString(2, user.password());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
    }

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM users where name = ?";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String password = rs.getString("password");

                return new User(userId, userName, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User get(int id) {
        String sql = "SELECT * FROM users where id = ?";
        try (Connection conn = sqliteConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String password = rs.getString("password");

                return new User(userId, userName, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
