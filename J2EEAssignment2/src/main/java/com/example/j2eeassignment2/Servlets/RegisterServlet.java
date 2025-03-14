package com.example.j2eeassignment2.Servlets;

import com.example.j2eeassignment2.DAOs.UserDAO;
import com.example.j2eeassignment2.Services.PasswordHasher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final PasswordHasher passwordHasher = new PasswordHasher();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPassword = passwordHasher.hashPassword(password);

        // Register user with the hashed password
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(firstName, lastName, email, hashedPassword);

        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
