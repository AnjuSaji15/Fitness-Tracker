package com.fitnesstracker.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ProgressTrackingServlet")
public class ProgressTrackingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get data from form
            String weightStr = request.getParameter("weight");
            String stepsStr = request.getParameter("steps");
            String exerciseMinutesStr = request.getParameter("exercise_minutes");

            // Check if any parameter is missing or empty
            if (weightStr == null || weightStr.trim().isEmpty() || 
                stepsStr == null || stepsStr.trim().isEmpty() || 
                exerciseMinutesStr == null || exerciseMinutesStr.trim().isEmpty()) {

                // If any field is missing, send a response with an error message
                response.sendRedirect("progressSummary.html?error=All fields must be filled.");
                return;
            }

            // Parse the values only after validating they are not null/empty
            double currentWeight = Double.parseDouble(weightStr.trim());  // Trim to avoid spaces
            int totalSteps = Integer.parseInt(stepsStr.trim());
            int totalExerciseMinutes = Integer.parseInt(exerciseMinutesStr.trim());

            // Calculate distance covered (e.g., assume a conversion for steps to distance)
            double distanceCovered = totalSteps * 0.0008;  // Example: steps to kilometers

            // Calculate calories burned (simple estimation)
            double caloriesBurned = totalExerciseMinutes * 0.04; // Example: calories per minute of exercise

            // Store the data in session or process further as needed
            HttpSession session = request.getSession();
            session.setAttribute("currentWeight", currentWeight);
            session.setAttribute("totalSteps", totalSteps);
            session.setAttribute("totalExerciseMinutes", totalExerciseMinutes);

            // Redirect to the progress summary page with calculated data
            response.sendRedirect("progressSummary.html?totalSteps=" + totalSteps + 
                "&totalDistance=" + distanceCovered + "&totalCalories=" + caloriesBurned);

        } catch (NumberFormatException e) {
            // Handle invalid number format (e.g., user enters non-numeric values)
            response.sendRedirect("progressSummary.html?error=Please enter valid numbers.");
        }
    }
}
