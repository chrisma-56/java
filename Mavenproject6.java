/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject6;

/**
 *
 * @author chris
 */
import java.sql.*;
import java.util.Scanner;
 

public class Mavenproject6 {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendencedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = establishConnection();
        if (connection == null) {
            System.out.println("Could not establish a connection to the database.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Conference Attendee Management System");
            System.out.println("1. Add Attendee");
            System.out.println("2. Edit Attendee");
            System.out.println("3. Delete Attendee");
            System.out.println("4. Search Attendee");
            System.out.println("5. Generate Statistics");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addAttendee(connection, scanner);
                case 2 -> editAttendee(connection, scanner);
                case 3 -> deleteAttendee(connection, scanner);
                case 4 -> searchAttendee(connection, scanner);
                case 5 -> generateStatistics(connection);
                case 6 -> exit = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        closeConnection(connection);
    }

    private static Connection establishConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return null;
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }

    private static void addAttendee(Connection connection, Scanner scanner) {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();

        String sql = "INSERT INTO attend (name, email, contact, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, contactNumber);
            ps.setString(4, country);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Attendee added with ID: " + rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding attendee: " + e.getMessage());
        }
    }

    private static void editAttendee(Connection connection, Scanner scanner) {
        System.out.print("Enter attendee ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new contact number: ");
        String contact = scanner.nextLine();

        String sql = "UPDATE attend SET email = ?, contact = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, contact);
            ps.setInt(3, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Attendee updated successfully.");
            } else {
                System.out.println("Attendee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating attendee: " + e.getMessage());
        }
    }

    private static void deleteAttendee(Connection connection, Scanner scanner) {
        System.out.print("Enter attendee ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM attend WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Attendee deleted successfully.");
            } else {
                System.out.println("Attendee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting attendee: " + e.getMessage());
        }
    }

    private static void searchAttendee(Connection connection, Scanner scanner) {
        System.out.println("Search by:");
        System.out.println("1. ID");
        System.out.println("2. Full Name");
        System.out.println("3. Country");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        String sql;
        switch (choice) {
            case 1 -> {
                System.out.print("Enter ID: ");
                sql = "SELECT * FROM attendees WHERE id = ?";
            }
            case 2 -> {
                System.out.print("Enter full name: ");
                sql = "SELECT * FROM attendees WHERE name = ?";
            }
            case 3 -> {
                System.out.print("Enter country: ");
                sql = "SELECT * FROM attendees WHERE country = ?";
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, scanner.nextLine());
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Search Results:");
                while (rs.next()) {
                    System.out.printf("ID: %d, Name: %s, Email: %s, Contact: %s, Country: %s%n",
                            rs.getInt("ID"),
                            rs.getString("FullName"),
                            rs.getString("Email"),
                            rs.getString("ContactNumber"),
                            rs.getString("Country"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching attendee: " + e.getMessage());
        }
    }

     private static void generateStatistics(Connection connection) {
        String sql = "SELECT Country, COUNT(ID) AS AttendeeCount FROM attendees GROUP BY Country";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Attendee Statistics:");
            while (rs.next()) {
                System.out.printf("Country: %s, Count: %d%n",
                        rs.getString("Country"),
                        rs.getInt("AttendeeCount"));
            }
        } catch (SQLException e) {
            System.out.println("Error generating statistics: " + e.getMessage());
        }
}
}
