<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    // Invalidate the session to log out the user
    HttpSession logged = request.getSession(false);
    if (logged != null) {
        session.invalidate();
    }

    // Redirect to the home page after 3 seconds
    response.setHeader("Refresh", "3; URL=index.jsp");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logging Out...</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="container text-center">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-body p-4">
                    <h2 class="mb-3">You have been logged out</h2>
                    <p class="text-muted">Redirecting you back to the home page...</p>
                    <div class="spinner-border text-primary mt-3" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                    <p class="mt-3"><a href="index.jsp" class="btn btn-primary">Return to Home</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
