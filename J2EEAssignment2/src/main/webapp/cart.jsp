<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.j2eeassignment2.DAOs.CartDAO" %>
<%@ page import="com.example.j2eeassignment2.Models.OrderItem" %>
<%@ page import="com.example.j2eeassignment2.Models.User" %>
<%@ page import="java.util.List" %>

<%
    // Retrieve user from session
    User user = (User) session.getAttribute("user");

    // Redirect to login if user is not logged in
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Get the cart items based on user ids
    int userId = user.getId();
    CartDAO cartDAO = new CartDAO();
    List<OrderItem> cartItems = cartDAO.getCartItems(userId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Cart</h2>

    <% if (cartItems.isEmpty()) { %>
    <div class="alert alert-warning text-center">Your cart is empty.</div>
    <% } else { %>
    <div class="card shadow-lg p-4">
        <ul class="list-group">
            <% for (OrderItem item : cartItems) { %>
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                    <h5 class="mb-1"><%= item.getProduct().getName() %></h5>
                    <p class="mb-1">Price: <strong>$<%= item.getProduct().getPrice() %></strong></p>
                    <p class="mb-1">Quantity: <strong><%= item.getQuantity() %></strong></p>
                    <p class="text-muted">Total: $<%= item.getTotalPrice() %></p>
                </div>
            </li>
            <% } %>
        </ul>

        <div class="text-center mt-4">
            <a href="checkout.jsp" class="btn btn-success btn-lg">Proceed to Checkout</a>
        </div>
    </div>
    <% } %>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-primary">Continue Shopping</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
