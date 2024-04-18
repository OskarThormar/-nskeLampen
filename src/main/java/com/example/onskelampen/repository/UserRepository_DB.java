package com.example.onskelampen.repository;

import com.example.onskelampen.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository_DB {
    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String uid;
    @Value("${spring.datasource.password}")
    String pwd;

    public int findUserId(String userName) {
        int userid = 0;
        String query = "SELECT userid FROM bruger WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(db_url, uid, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userid = resultSet.getInt("userid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userid;
    }

    public boolean register(User newUser) {
        String query = "INSERT INTO bruger (username, userpassword) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(db_url, uid, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newUser.getUserName());
            preparedStatement.setString(2, newUser.getPassword());

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT COUNT(*) AS count FROM bruger WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(db_url, uid, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean loginUser(String username, String password) {
        String query = "SELECT COUNT(*) AS count FROM bruger WHERE username = ? AND userpassword = ?";

        try (Connection connection = DriverManager.getConnection(db_url, uid, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}