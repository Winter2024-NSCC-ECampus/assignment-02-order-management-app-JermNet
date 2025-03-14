package com.example.j2eeassignment2.Servlets;

import com.example.j2eeassignment2.DAOs.CartDAO;
import com.example.j2eeassignment2.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Don't create a new session
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getId();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Save the cart item in the database
        CartDAO cartDAO = new CartDAO();
        cartDAO.addToCart(productId, quantity, userId);

        // Redirect to cart page
        response.sendRedirect("cart.jsp");
    }
}
