package ch.bbcag.tokenstorer.db;

import ch.bbcag.tokenstorer.user.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO user (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
        }
    }

    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT password_hash FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString("password_hash");
                    return BCrypt.checkpw(password, hashedPasswordFromDB);
                }
            }
        }
        return false;
    }

}
