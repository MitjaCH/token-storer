package ch.bbcag.tokenstorer.model;

public class Token {
    private String provider;
    private String token;
    private String createdAt;

    public Token(String provider, String token, String createdAt) {
        this.provider = provider;
        this.token = token;
        this.createdAt = createdAt;
    }

    public String getProvider() {
        return provider;
    }

    public String getToken() {
        return token;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
