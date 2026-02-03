package org.example.employeemanagementsystem;

import org.example.employeemanagementsystem.model.User;
import org.example.employeemanagementsystem.ui.LoginUI;
import org.example.employeemanagementsystem.ui.MainMenu;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|------------------------------|");
        System.out.println("|                              |");
        System.out.println("| Projekti EMPLOYEE MANAGMENT  |");
        System.out.println("|                              |");
        System.out.println("|------------------------------|");
        System.out.println("");


        // logini
        LoginUI loginUI = new LoginUI();
        User currentUser = loginUI.performLogin(scanner);

        if (currentUser != null) {

            MainMenu mainMenu = new MainMenu(currentUser);
            mainMenu.display(scanner);
        }

        scanner.close();
        System.out.println("Application Ended!");
    }
}
