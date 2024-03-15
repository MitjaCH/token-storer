package ch.bbcag.tokenstorer.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    private static final int DEFAULT_WORK_FACTOR = 12;

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(DEFAULT_WORK_FACTOR));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String hashToken(String token) {
        return BCrypt.hashpw(token, BCrypt.gensalt(DEFAULT_WORK_FACTOR));
    }

    public static boolean checkToken(String token, String hashedToken) {
        return BCrypt.checkpw(token, hashedToken);
    }
}
