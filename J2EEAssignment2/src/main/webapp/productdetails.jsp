<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.j2eeassignment2.DAOs.ProductDAO" %>
<%@ page import="com.example.j2eeassignment2.Models.Product" %>
<%@ page import="java.util.List" %>

<%
    ProductDAO productDAO = new ProductDAO();
    List<Product> products = productDAO.getAllProducts();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">All Products</h2>

    <% if (products.isEmpty()) { %>
    <div class="alert alert-info text-center">No products available.</div>
    <% } else { %>
    <div class="row">
        <% for (Product product : products) { %>
        <div class="col-md-4">
            <div class="card shadow-lg mb-4">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <p><strong>Price:</strong> $<%= product.getPrice() %></p>
                    <p><strong>Stock:</strong> <%= product.getStock() %></p>

                    <% if (product.getStock() > 0) { %>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                        <div class="input-group mb-3">
                            <input type="number" name="quantity" class="form-control" min="1" max="<%= product.getStock() %>" required>
                            <button type="submit" class="btn btn-primary">Buy Now</button>
                        </div>
                    </form>
                    <% } else { %>
                    <p class="text-danger fw-bold">Out of Stock</p>
                    <% } %>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <% } %>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
