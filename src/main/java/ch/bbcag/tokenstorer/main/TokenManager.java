package ch.bbcag.tokenstorer.main;

import ch.bbcag.tokenstorer.db.db_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TokenManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static int userId;


    public boolean register(String username, String password) {
        try (Connection conn = db_conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password) {
        try (Connection conn = db_conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void displayMenu() {
        if (!loggedIn) {
            System.out.println("[LOGIN]");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("[MITJA'S TOKEN-STORER]");
            System.out.println("[1] Add Token");
            System.out.println("[2] Remove Token");
            System.out.println("[3] Get Token");
            System.out.println("[4] Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.println("Register:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        db_conn.registerUser(username, password);
    }

    private static void login() {
        System.out.println("Login:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = db_conn.loginUser(username, password);
        if (success) {
            System.out.println("Login successful!");
            loggedIn = true;
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

}
