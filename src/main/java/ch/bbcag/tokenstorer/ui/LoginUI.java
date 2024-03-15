// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ch.bbcag.tokenstorer.db.DatabaseManager;
import ch.bbcag.tokenstorer.security.PasswordManager;

public class LoginUI {
    public static void loginUser(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String selectUserSQL = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (PasswordManager.checkPassword(password, hashedPassword)) {
                    System.out.println("Login successful!");

                    TokenManagementUI.manageTokens(conn, rs.getInt("user_id"));
                } else {
                    System.out.println("Invalid password.");
                }
            } else {
                System.out.println("User not found.");
            }
        }
    }
}
