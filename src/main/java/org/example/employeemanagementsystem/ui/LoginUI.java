package org.example.employeemanagementsystem.ui;

import org.example.employeemanagementsystem.model.User;
import org.example.employeemanagementsystem.service.AuthenticationService;

import java.util.Scanner;

public class LoginUI {

    private AuthenticationService authService;

    public LoginUI(){
        this.authService = new AuthenticationService();
    }

    // qitu osht loginscreeni edhe me bo login

    public User performLogin(Scanner scanner){
        System.out.println("---------------------------");
        System.out.println(" EMPLOYE MANAGEMENT - LOGIN");
        System.out.println("---------------------------");

        int attempts = 0;
        int maxAttempts = 3;

        while(attempts < maxAttempts) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            User user = authService.login(username, password);

            if (user != null) {
                System.out.println("---------------------------");
                System.out.println("Logged in as : " + user.getUsername());
                System.out.println("Role : " + user.getRoleType());
                System.out.println("---------------------------");
                return user;
            } else{
                attempts++;
                int remaining = maxAttempts - attempts;
                if (remaining > 0) {
                    System.out.println("Remaining attempts: " + remaining);
                }
            }
        }

        System.out.println("Maximum login attempts exceeded!");
        System.out.println("Please contact Elion AgushiTEAM support");
        return null;
    }
}
