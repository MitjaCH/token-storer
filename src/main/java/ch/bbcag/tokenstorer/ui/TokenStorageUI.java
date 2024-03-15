// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TokenStorageUI {
    public static void storeToken(Connection conn, int userId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Store Token ===");
        System.out.print("Token Name: ");
        String provider = scanner.nextLine();
        System.out.print("Access Token: ");
        String accessToken = scanner.nextLine();

        String encryptedToken = accessToken;

        String insertTokenSQL = "INSERT INTO token (user_id, token_provider, token_hashed) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertTokenSQL)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, provider);
            pstmt.setString(3, encryptedToken);
            pstmt.executeUpdate();
            System.out.println("Token stored successfully!");
        }
    }
}
