// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenViewUI {
    public static void viewTokens(Connection conn, int userId) throws SQLException {
        System.out.println("\n=== View Tokens ===");
        String selectTokensSQL = "SELECT * FROM token WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectTokensSQL)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String provider = rs.getString("token_provider");
                String encryptedToken = rs.getString("token_hashed");
                // Decrypt the token before displaying (you need to implement the decryption logic)
                String decryptedToken = encryptedToken; // You need to implement decryption
                System.out.println("Provider: " + provider + ", Token: " + decryptedToken);
            }
        }
    }
}
