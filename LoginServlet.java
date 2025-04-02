package com.fitnesstracker.servlet;

import com.fitnesstracker.dao.UserDao;
import java.sql.*;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        if (userDao.validateUser(username, password)) {
            response.sendRedirect("healthinfo.html"); // Redirects to home page
        } else {
            response.getWriter().println("Invalid username or password. <a href='login.html'>Try again</a>");
        }
    }
}
