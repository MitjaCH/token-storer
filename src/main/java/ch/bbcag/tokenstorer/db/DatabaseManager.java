package ch.bbcag.tokenstorer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost/tokenstorer";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
