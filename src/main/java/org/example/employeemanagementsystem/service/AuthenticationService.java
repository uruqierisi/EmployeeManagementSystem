package org.example.employeemanagementsystem.service;

import org.example.employeemanagementsystem.dao.UserDAO;
import org.example.employeemanagementsystem.model.User;

public class AuthenticationService {

    private UserDAO userDAO;

    public AuthenticationService() {
        this.userDAO = new UserDAO();
    }

    // logini me userpw
    public User login(String username, String password) {
        // Validation
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Error: Username cannot be empty!");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Error: Password cannot be empty!");
            return null;
        }

        // Get user from database
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            System.out.println("✗ Login failed: Invalid username!");
            return null;
        }

        // Check password
        if (!user.getPassword().equals(password)) {
            System.out.println("✗ Login failed: Invalid password!");
            return null;
        }

        // Check if user is active
        if (!user.isActive()) {
            System.out.println("✗ Login failed: Account is deactivated!");
            return null;
        }

        // Update last login time
        userDAO.updateLastLogin(user.getUserId());

        System.out.println("✓ Login successful! Welcome, " + username);

        return user;
    }

    // logout
    public void logout(User user) {
        if (user != null) {
            System.out.println("Goodbye, " + user.getUsername() + "!");
        }
    }
}
