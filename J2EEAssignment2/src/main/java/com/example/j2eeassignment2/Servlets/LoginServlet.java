package com.example.j2eeassignment2.Servlets;

import com.example.j2eeassignment2.DAOs.UserDAO;
import com.example.j2eeassignment2.Models.User;
import com.example.j2eeassignment2.Services.PasswordHasher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final PasswordHasher passwordHasher = new PasswordHasher();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashedPassword = passwordHasher.hashPassword(password);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(email, hashedPassword);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", "Invalid credentials");
            response.sendRedirect("login.jsp");
        }
    }
}
