package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/SignupFormservlet")
public class SignupFormservlet extends HttpServlet {
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,6}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String birthdate = request.getParameter("birthdate");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        
        boolean hasErrors = false;
        
       
        if (username == null || username.trim().isEmpty()) 
        {
            request.setAttribute("usernameError", "Username is required.");
            hasErrors = true;
        } else if (!Pattern.matches(USERNAME_REGEX, username)) 
        {
            request.setAttribute("usernameError", 
                "Username must be 3-20 characters long and can only contain letters, numbers, and underscore.");
            hasErrors = true;
        }
        
        
        if (phone == null || phone.trim().isEmpty()) 
        {
            request.setAttribute("phoneError", "Phone number is required.");
            hasErrors = true;
        } else if (!Pattern.matches(PHONE_REGEX, phone)) 
        {
            request.setAttribute("phoneError", "Phone number must be exactly 10 digits.");
            hasErrors = true;
        }
        
      
        if (birthdate == null || birthdate.trim().isEmpty()) 
        {
            request.setAttribute("birthdateError", "Birthdate is required.");
            hasErrors = true;
        } 
        else {
            try {
                LocalDate dob = LocalDate.parse(birthdate);
                LocalDate now = LocalDate.now();
                LocalDate minAge = now.minusYears(13); // Minimum age requirement of 13
                
                if (dob.isAfter(now)) 
                {
                    request.setAttribute("birthdateError", "Birthdate cannot be in the future.");
                    hasErrors = true;
                } else if (dob.isAfter(minAge)) 
                {
                    request.setAttribute("birthdateError", "You must be at least 13 years old to register.");
                    hasErrors = true;
                }
            } catch (DateTimeParseException e) 
            {
                request.setAttribute("birthdateError", "Invalid date format. Please use YYYY-MM-DD format.");
                hasErrors = true;
            }
        }
        
       
        if (email == null || email.trim().isEmpty()) 
        {
            request.setAttribute("emailError", "Email is required.");
            hasErrors = true;
        } else if (!Pattern.matches(EMAIL_REGEX, email)) 
        {
            request.setAttribute("emailError", "Please enter a valid email address.");
            hasErrors = true;
        }
        
        
        if (gender == null || gender.trim().isEmpty()) 
        {
            request.setAttribute("genderError", "Gender selection is required.");
            hasErrors = true;
        } else if (!gender.equals("male") && !gender.equals("female") && !gender.equals("other")) 
        {
            request.setAttribute("genderError", "Invalid gender selection.");
            hasErrors = true;
        }
        
        
        if (password == null || password.trim().isEmpty()) 
        {
            request.setAttribute("passwordError", "Password is required.");
            hasErrors = true;
        } else if (!Pattern.matches(PASSWORD_REGEX, password)) 
        {
            request.setAttribute("passwordError", 
                "Password must be at least 8 characters long and contain at least one uppercase letter, " +
                "one lowercase letter, one number, and one special character (@#$%^&+=)");
            hasErrors = true;
        }
        
       
        if (hasErrors)  
        {
            request.getRequestDispatcher("Signupform.jsp").forward(request, response);
        } else 
        {
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lenskart", "root", "rootroot")) {
                    String sql = "INSERT INTO signup (username,phone,birthdate,email,gender,password) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1,username);
                        pstmt.setString(2, phone);
                        pstmt.setDate(3, java.sql.Date.valueOf(birthdate));
                        pstmt.setString(4, email);
                        pstmt.setString(5, gender);
                        pstmt.setString(6, password);                        
                        pstmt.executeUpdate();
                    }
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("databaseError", "There was an error processing your request. Please try again.");
                request.getRequestDispatcher("Signupform.jsp").forward(request, response);
            }
        }
    }
}