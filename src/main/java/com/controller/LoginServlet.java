package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "password";
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException 
	    {
	        
	        // Get parameters from request
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        try 
	        {
	            // Establish database connection
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lenskart","root","rootroot");
	            
	            // Prepare and execute query
	            String sql = "SELECT * FROM signup WHERE email = ? AND password = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, email);
	            stmt.setString(2, password);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            // Check if user exists
	            if (rs.next()) 
	            {
	                // Create session
	                HttpSession session = request.getSession();
	                session.setAttribute("email", email);
	                
	                // Redirect to welcome page
	                response.sendRedirect("welcome.jsp");
	            } 
	            else 
	            {
	                // Set error message in session and redirect
	                request.getSession().setAttribute("error", "Invalid login");
	                response.sendRedirect("Login.jsp");
	            }
	            
	            // Close resources
	            rs.close();
	            stmt.close();
	            conn.close();
	            
	        } 
	        catch (Exception e) 
	        {
	            // Handle any errors using session
	            request.getSession().setAttribute("error", "Database connection error");
	            response.sendRedirect("Login.jsp");
	            e.printStackTrace();
	        }
	    }
}
