package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TokenManagementUI {
    public static void manageTokens(Connection conn, int userId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Store Token");
            System.out.println("2. View Tokens");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    TokenStorageUI.storeToken(conn, userId);
                    break;
                case 2:
                    TokenViewUI.viewTokens(conn, userId);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
