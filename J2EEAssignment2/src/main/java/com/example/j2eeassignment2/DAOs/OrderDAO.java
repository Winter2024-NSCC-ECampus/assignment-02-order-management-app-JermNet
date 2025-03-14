package com.example.j2eeassignment2.DAOs;

import com.example.j2eeassignment2.Services.DBConnection;
import com.example.j2eeassignment2.Models.Order;
import com.example.j2eeassignment2.Models.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final Connection connection;

    // Get the connection
    public OrderDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create an order by inserting
    public int createOrder(int userId, String firstName, String lastName, String city, String street, String landmark, String state, String pin, String phone) {
        String sql = "INSERT INTO orders (user_id, first_name, last_name, city, street, landmark, state, pin, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, city);
            stmt.setString(5, street);
            stmt.setString(6, landmark);
            stmt.setString(7, state);
            stmt.setString(8, pin);
            stmt.setString(9, phone);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Add items to an order from the cart
    public void addOrderItems(int orderId, List<OrderItem> cartItems) {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (OrderItem item : cartItems) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, item.getProduct().getId());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getProduct().getPrice());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the orders from a user account
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT id, order_date, first_name, last_name, city, street, landmark, state, pin, phone FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        userId,
                        rs.getTimestamp("order_date"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("landmark"),
                        rs.getString("state"),
                        rs.getString("pin"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Place an order using a transaction, first insert into the orders table which contains info about the order, then order_items which contains the list of items in the order
    public boolean placeOrder(Order order, List<OrderItem> cartItems) {
        String insertOrderSQL = "INSERT INTO orders (user_id, order_date, first_name, last_name, city, street, landmark, state, pin, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertOrderItemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement orderStmt = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getUserId());
                orderStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                orderStmt.setString(3, order.getFirstName());
                orderStmt.setString(4, order.getLastName());
                orderStmt.setString(5, order.getCity());
                orderStmt.setString(6, order.getStreet());
                orderStmt.setString(7, order.getLandmark());
                orderStmt.setString(8, order.getState());
                orderStmt.setString(9, order.getPin());
                orderStmt.setString(10, order.getPhone());

                int affectedRows = orderStmt.executeUpdate();
                if (affectedRows == 0) {
                    connection.rollback();
                    return false;
                }

                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        //orderId = generatedKeys.getInt(1);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }

            // Insert order items
            try (PreparedStatement itemStmt = connection.prepareStatement(insertOrderItemSQL)) {
                for (OrderItem item : cartItems) {
                    itemStmt.setInt(1, order.getId());
                    itemStmt.setInt(2, item.getProduct().getId());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setDouble(4, item.getProduct().getPrice());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
        return false;
    }
}
