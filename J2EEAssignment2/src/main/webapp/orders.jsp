<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.j2eeassignment2.DAOs.OrderDAO" %>
<%@ page import="com.example.j2eeassignment2.Models.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.j2eeassignment2.Models.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    OrderDAO orderDAO = new OrderDAO();
    List<Order> orders = orderDAO.getOrdersByUser(user.getId());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Orders</h2>

    <% if (orders.isEmpty()) { %>
    <div class="alert alert-info text-center">You haven't placed any orders yet.</div>
    <% } else { %>
    <div class="row">
        <% for (Order order : orders) { %>
        <div class="col-md-6">
            <div class="card shadow-lg mb-4">
                <div class="card-body">
                    <h5 class="card-title">Order ID: <%= order.getId() %></h5>
                    <p class="card-text"><b>Date:</b> <%= order.getOrderDate() %></p>
                    <h6 class="card-subtitle mb-2 text-muted">Shipping Details:</h6>
                    <p><b>Name:</b> <%= order.getFirstName() %> <%= order.getLastName() %></p>
                    <p><b>Address:</b> <%= order.getStreet() %>, <%= order.getLandmark() %>, <%= order.getCity() %>, <%= order.getState() %>, PIN: <%= order.getPin() %></p>
                    <p><b>Phone:</b> <%= order.getPhone() %></p>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <% } %>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-primary">Back to Home</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
