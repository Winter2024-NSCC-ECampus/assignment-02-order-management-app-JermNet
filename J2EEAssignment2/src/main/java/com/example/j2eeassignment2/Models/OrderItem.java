package com.example.j2eeassignment2.Models;

// An order item, which is meant to go along with an order, as a table can't properly be a list, it connects to multiple elements in the OrderItem table
public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
