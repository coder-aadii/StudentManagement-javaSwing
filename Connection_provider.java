package com.manage_student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_provider {
    static Connection con;

    public static Connection create_connection() {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection
            String url = "jdbc:mysql://localhost:3306/student_manage"; // Ensure the database name matches
            String user = "root"; // Update with your MySQL username
            String password = "admin123"; // Update with your MySQL password
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connection established successfully.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed.");
        }

        return con;
    }
}
