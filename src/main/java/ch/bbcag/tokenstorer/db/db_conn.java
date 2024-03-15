package ch.bbcag.tokenstorer.db;

import ch.bbcag.tokenstorer.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class db_conn {
    private static final String DEFAULT_JDBC_URL = "";

    public static void loadEnv() {
        try {
            Scanner scanner = new Scanner(new File(".env"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    System.setProperty(key, value);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(".env file not found. Please create one with DB_USERNAME, DB_PASSWORD, and JDBC_URL entries.");
        }
    }

    public static Connection getConnection() throws SQLException {
        loadEnv();
        String username = "root";
        String password = "1234";
        String jdbcUrl = "jdbc:mysql://localhost:3306/tokenstorer";
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            jdbcUrl = DEFAULT_JDBC_URL;
        }
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
            throw e;
        }
    }

    public static void registerUser(String username, String password) {
        try (Connection connection = getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            userDAO.registerUser(new User(username, password));
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean loginUser(String username, String password) {
        try (Connection connection = getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            return userDAO.loginUser(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
