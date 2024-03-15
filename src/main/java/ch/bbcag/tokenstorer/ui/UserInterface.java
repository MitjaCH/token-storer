// UI package: tokenstorer.ui
package ch.bbcag.tokenstorer.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import ch.bbcag.tokenstorer.db.DatabaseManager;

public class UserInterface {
    public static void start() {
        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println("Connected to database");

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                System.out.println(Colors.CYAN+"[MITJA'S TOKEN STORER]"+Colors.RESET);
                System.out.println(Colors.MAGENTA+"\n[1] Register"+Colors.RESET);
                System.out.println(Colors.YELLOW+"[2] Login"+Colors.RESET);
                System.out.println(Colors.RED+"[3] Exit"+Colors.RESET);
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        RegisterUI.registerUser(conn);
                        break;
                    case 2:
                        LoginUI.loginUser(conn);
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
