package ch.bbcag.tokenstorer.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
