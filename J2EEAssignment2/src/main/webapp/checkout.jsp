<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Enter Your Shipping Details</h2>

    <div class="card shadow-lg p-4">
        <form action="CheckoutServlet" method="post">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" id="firstName" name="firstName" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" id="lastName" name="lastName" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <input type="text" id="city" name="city" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="street" class="form-label">Street</label>
                <input type="text" id="street" name="street" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="landmark" class="form-label">Landmark (Optional)</label>
                <input type="text" id="landmark" name="landmark" class="form-control">
            </div>

            <div class="mb-3">
                <label for="state" class="form-label">State</label>
                <input type="text" id="state" name="state" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="pin" class="form-label">PIN Code</label>
                <input type="text" id="pin" name="pin" class="form-control" required pattern="[0-9]{6}">
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone Number</label>
                <input type="text" id="phone" name="phone" class="form-control" required pattern="[0-9]{10}">
            </div>

            <button type="submit" class="btn btn-success w-100">Place Order</button>
        </form>
    </div>

    <div class="text-center mt-4">
        <a href="cart.jsp" class="btn btn-secondary">Back to Cart</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
