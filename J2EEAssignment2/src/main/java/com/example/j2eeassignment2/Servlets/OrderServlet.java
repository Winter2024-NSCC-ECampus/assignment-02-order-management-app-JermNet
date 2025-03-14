package com.example.j2eeassignment2.Servlets;

import com.example.j2eeassignment2.DAOs.CartDAO;
import com.example.j2eeassignment2.DAOs.OrderDAO;
import com.example.j2eeassignment2.Models.Order;
import com.example.j2eeassignment2.Models.OrderItem;
import com.example.j2eeassignment2.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve shipping details from form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String landmark = request.getParameter("landmark");
        String state = request.getParameter("state");
        String pin = request.getParameter("pin");
        String phone = request.getParameter("phone");

        if (firstName == null || lastName == null || city == null || street == null || state == null || pin == null || phone == null) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        // Retrieve cart items for the user
        CartDAO cartDAO = new CartDAO();
        List<OrderItem> cartItems = cartDAO.getCartItems(user.getId());

        if (cartItems.isEmpty()) {
            request.setAttribute("errorMessage", "Your cart is empty.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        // Create new order
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order(
                0,
                user.getId(),
                new Timestamp(System.currentTimeMillis()),
                firstName,
                lastName,
                city,
                street,
                landmark,
                state,
                pin,
                phone
        );

        // Save order in database
        boolean orderPlaced = orderDAO.placeOrder(order, cartItems);

        if (orderPlaced) {
            // Clear the cart after successful order placement
            cartDAO.clearCart(user.getId());

            response.sendRedirect("orders.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to place order.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }
}
