package ch.bbcag.tokenstorer.db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("USER");
    private static final String PASS = dotenv.get("PASS");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
