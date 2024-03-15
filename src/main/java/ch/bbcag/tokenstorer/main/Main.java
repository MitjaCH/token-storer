package ch.bbcag.tokenstorer.main;

public class Main {
    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManager();
        while (true) {
            tokenManager.displayMenu();
        }
    }
}
