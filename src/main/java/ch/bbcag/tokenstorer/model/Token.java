package ch.bbcag.tokenstorer.model;

import java.sql.Timestamp;

public class Token {
    private int tokenId;
    private int userId;
    private String tokenProvider;
    private String tokenHashed;
    private Timestamp createdAt;

}
