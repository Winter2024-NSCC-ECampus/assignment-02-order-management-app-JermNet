package com.example.j2eeassignment2.DAOs;

import com.example.j2eeassignment2.Services.DBConnection;
import com.example.j2eeassignment2.Models.OrderItem;
import com.example.j2eeassignment2.Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private final Connection connection;

    // Setup connection
    public CartDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Add an item to the cart with the connection
    public void addToCart(int productId, int quantity, int userId) {
        String sql = "INSERT INTO cart (product_id, quantity, user_id) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, quantity);
            stmt.setInt(3, userId);

            // Increment the product if it already exists
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get items from the cart, adding them to an arraylist
    public List<OrderItem> getCartItems(int userId) {
        List<OrderItem> cartItems = new ArrayList<>();
        String sql = "SELECT c.product_id, c.quantity, p.name, p.price FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"));
                cartItems.add(new OrderItem(product, rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Clear the cart
    public void clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
