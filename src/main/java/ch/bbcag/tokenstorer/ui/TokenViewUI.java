// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import tokenstorer.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenViewUI {
    public static void viewTokens(Connection conn, int userId) throws SQLException {
        System.out.println("\n|===== View Tokens =====|");

        User user = new User();
        user.setUserId(userId);

        try {
            user.getTokens(conn).forEach(token -> {
                System.out.println("\nName: "+ token.getProvider() + "\nToken: "+ token.getToken() +"\nCreated at: "+token.getCreatedAt());
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
