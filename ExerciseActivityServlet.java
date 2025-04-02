package com.fitnesstracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ExerciseActivityServlet")
public class ExerciseActivityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from form
        int steps = Integer.parseInt(request.getParameter("steps"));
        int exerciseDuration = Integer.parseInt(request.getParameter("exercise_duration"));
        int caloriesBurned = Integer.parseInt(request.getParameter("calories_burned"));

        // Process or store the data (e.g., store in database or session)
        HttpSession session = request.getSession();
        session.setAttribute("steps", steps);
        session.setAttribute("exerciseDuration", exerciseDuration);
        session.setAttribute("caloriesBurned", caloriesBurned);

        // Redirect to the progress page or dashboard
        response.sendRedirect("progressSummary.html"); // Example redirect
    }
}
