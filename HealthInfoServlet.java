package com.fitnesstracker.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HealthInfoServlet")
public class HealthInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from form
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));

        // Calculate BMI (Height in meters, Weight in kg)
        double heightInMeters = height / 100;
        double bmi = weight / (heightInMeters * heightInMeters);

        // Store information in session or database
        HttpSession session = request.getSession();
        session.setAttribute("userName", name);
        session.setAttribute("userBMI", bmi);

        // Forward to another page (optional)
        response.sendRedirect("fitnessgoals.html");
    }
}
