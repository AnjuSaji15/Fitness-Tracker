package com.fitnesstracker.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FitnessGoalsServlet")
public class FitnessGoalsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from form
        String weightGoal = request.getParameter("weight_goal");
        String stepGoalParam = request.getParameter("step_goal");
        String fitnessGoal = request.getParameter("fitness_goal");

        // Validate and parse input values
        if (stepGoalParam != null && !stepGoalParam.isEmpty()) {
            try {
                int stepGoal = Integer.parseInt(stepGoalParam);
                if (stepGoal <= 0) {
                    response.sendRedirect("fitnessgoals.html?error=Invalid step goal");
                    return;
                }

                // Store in session
                HttpSession session = request.getSession();
                session.setAttribute("weightGoal", weightGoal);
                session.setAttribute("stepGoal", stepGoal);
                session.setAttribute("fitnessGoal", fitnessGoal);

                // Redirect to the next page or dashboard
                response.sendRedirect("activitydata.html");

            } catch (NumberFormatException e) {
                response.sendRedirect("fitnessgoals.html?error=Invalid number format for step goal");
            }
        } else {
            response.sendRedirect("fitnessgoals.html?error=Step goal is required");
        }
    }
}