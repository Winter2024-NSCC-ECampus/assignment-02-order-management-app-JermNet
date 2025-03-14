package com.example.j2eeassignment2.Servlets;

import com.example.j2eeassignment2.DAOs.CartDAO;
import com.example.j2eeassignment2.DAOs.OrderDAO;
import com.example.j2eeassignment2.DAOs.ProductDAO;
import com.example.j2eeassignment2.Models.OrderItem;
import com.example.j2eeassignment2.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        // Get shipping details from form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String landmark = request.getParameter("landmark");
        String state = request.getParameter("state");
        String pin = request.getParameter("pin");
        String phone = request.getParameter("phone");

        CartDAO cartDAO = new CartDAO();
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();

        // Fetch cart items using userId
        List<OrderItem> cartItems = cartDAO.getCartItems(userId);

        if (!cartItems.isEmpty()) {
            for (OrderItem item : cartItems) {
                int productId = item.getProduct().getId();
                int stock = productDAO.getStock(productId);

                if (stock < item.getQuantity()) {
                    request.setAttribute("errorMessage", "Not enough stock for: " + item.getProduct().getName());
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }
            }

            // Subtract stock for each item
            for (OrderItem item : cartItems) {
                int productId = item.getProduct().getId();
                int newStock = productDAO.getStock(productId) - item.getQuantity();
                productDAO.updateStock(productId, newStock);
            }

            // Create order with shipping details
            int orderId = orderDAO.createOrder(userId, firstName, lastName, city, street, landmark, state, pin, phone);
            orderDAO.addOrderItems(orderId, cartItems);

            // Clear cart after stock is updated
            cartDAO.clearCart(userId);
        }

        response.sendRedirect("orders.jsp");
    }
}
