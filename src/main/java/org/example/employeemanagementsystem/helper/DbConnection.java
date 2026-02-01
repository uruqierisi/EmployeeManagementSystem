package org.example.employeemanagementsystem.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL =
            "jdbc:sqlserver://ERIS:1433;databaseName=EmployeeDB;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "eu";
    private static final String PASSWORD = "eu";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed!", e);
        }
    }


    public static void main(String[] args) {
        try (Connection conn = connect()) {
            System.out.println("✅ Connected to SQL Server successfully!");
            System.out.println("DB: " + conn.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
