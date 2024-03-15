// Model package: tokenstorer.model
package tokenstorer.model;

import ch.bbcag.tokenstorer.model.Token;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private Timestamp createdAt;
    private List<Token> tokens;


    public List<Token> getTokens(Connection conn) throws SQLException {
        tokens = new ArrayList<>();
        String selectTokensSQL = "SELECT * FROM token WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectTokensSQL)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String provider = rs.getString("token_provider");
                String encryptedToken = rs.getString("token_hashed");
                String createdAt = rs.getString("created_at");
                String decryptedToken = encryptedToken;
                tokens.add(new Token(provider, decryptedToken, createdAt));
            }
        }
        return tokens;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
