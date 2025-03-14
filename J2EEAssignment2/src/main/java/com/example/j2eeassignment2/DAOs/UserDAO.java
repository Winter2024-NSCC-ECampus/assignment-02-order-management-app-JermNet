package com.example.j2eeassignment2.DAOs;

import com.example.j2eeassignment2.Services.DBConnection;
import com.example.j2eeassignment2.Models.User;

import java.sql.*;

public class UserDAO {
    private final Connection connection;

    // Get DB connection
    public UserDAO() {
        connection = DBConnection.getConnection();
    }

    // Validate user login
    public User validateUser(String email, String hashedPassword) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (storedHash.equals(hashedPassword)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Register new ser
    public boolean registerUser(String firstName, String lastName, String email, String password) {
        String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

