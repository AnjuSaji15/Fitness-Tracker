package com.fitnesstracker.servlet;

import com.fitnesstracker.dao.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().println("<script>alert('Registration Successful!'); window.location='login.html';</script>");
                } else {
                    response.getWriter().println("<script>alert('Registration Failed. Please try again.'); window.location='register.html';</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print SQL error for debugging
            response.getWriter().println("<script>alert('Database Error: " + e.getMessage() + "'); window.location='register.html';</script>");
        }
    }
}