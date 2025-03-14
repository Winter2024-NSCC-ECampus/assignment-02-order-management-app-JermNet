<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.j2eeassignment2.DAOs.ProductDAO" %>
<%@ page import="com.example.j2eeassignment2.Models.Product" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.Comparator" %>

<%
    ProductDAO productDAO = new ProductDAO();
    List<Product> products = productDAO.getAllProducts();
    HttpSession loggedin = request.getSession();

    // Check if the user is logged in
    Object user = loggedin.getAttribute("user");

    // Determine what products should be sorted by
    String sortBy = request.getParameter("sortBy");

    // Determine how products should be sorted
    String sortOrder = request.getParameter("sortOrder");

    // The actual sorting logic itself
    if (sortBy != null && !sortBy.isEmpty()) {
        if ("name".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                products.sort(Comparator.comparing(Product::getName)); // Ascending
            } else {
                products.sort((p1, p2) -> p2.getName().compareTo(p1.getName())); // Descending
            }
        } else if ("price".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                products.sort(Comparator.comparingDouble(Product::getPrice)); // Ascending
            } else {
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())); // Descending
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store (Very Cool)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Welcome To The Product Procurement Paradise</h2>
    <div class="d-flex justify-content-center mb-4">
        <button class="btn btn-primary mx-2" onclick="location.href='orders.jsp'">View Orders</button>
        <!--Use if statement to make buttons appear depending on the state of the user -->
        <% if (user == null) { %>
        <button class="btn btn-success mx-2" onclick="location.href='login.jsp'">Login</button>
        <button class="btn btn-warning mx-2" onclick="location.href='register.jsp'">Register</button>
        <% } else { %>
        <button class="btn btn-danger mx-2" onclick="location.href='logout.jsp'">Logout</button>
        <% } %>
    </div>

    <div class="mb-4 d-flex justify-content-center">
        <form action="index.jsp" method="get" class="d-flex">
            <select name="sortBy" class="form-select mx-2">
                <option value="name" <%= "name".equals(sortBy) ? "selected" : "" %>>Sort by Name</option>
                <option value="price" <%= "price".equals(sortBy) ? "selected" : "" %>>Sort by Price</option>
            </select>
            <select name="sortOrder" class="form-select mx-2">
                <option value="asc" <%= "asc".equals(sortOrder) ? "selected" : "" %>>Ascending</option>
                <option value="desc" <%= "desc".equals(sortOrder) ? "selected" : "" %>>Descending</option>
            </select>
            <button type="submit" class="btn btn-info">Sort</button>
        </form>
    </div>

    <h3 class="mb-3">Products</h3>
    <ul class="list-group">
        <% for (Product product : products) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <div>
                <a href="productdetails.jsp?id=<%= product.getId() %>">
                    <%= product.getName() %> - $<%= product.getPrice() %>
                </a>
            </div>
            <% if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) { %>
            <img src="<%= product.getImageUrl() %>" alt="Product Image" class="img-thumbnail" style="max-width: 100px;">
            <% } %>
        </li>
        <% } %>
    </ul>

    <!--Let a user add a product (not really "real world" but extremely useful for this)-->
    <% if (user != null) { %>
    <h3 class="mt-5">Add a New Product</h3>
    <form action="ProductServlet" method="post" class="border p-4 rounded">
        <div class="mb-3">
            <label for="name" class="form-label">Product Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea id="description" name="description" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" id="price" name="price" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="stock" class="form-label">Stock</label>
            <input type="number" id="stock" name="stock" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="image_url" class="form-label">Image URL</label>
            <input type="text" id="image_url" name="image_url" class="form-control">
        </div>

        <button type="submit" class="btn btn-success">Add Product</button>
    </form>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
