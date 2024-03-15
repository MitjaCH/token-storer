// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import ch.bbcag.tokenstorer.security.PasswordManager;

public class RegisterUI {
    public static void registerUser(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Colors.CYAN + "\n=== Register ===" + Colors.RESET);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String hashedPassword = PasswordManager.hashPassword(password);

        String insertUserSQL = "INSERT INTO user (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            System.out.println(Colors.GREEN + "User registered successfully!" + Colors.RESET);
        } catch (SQLException e) {
            System.out.println(Colors.RED + "Error registering user: " + e.getMessage() + Colors.RESET);
        }
    }
}
