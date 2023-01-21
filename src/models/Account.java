package models;

public class Account {
    private final String username;
    private final String base64EncodedHash;
    private final String base64EncodedSalt;

    public Account(String username, String base64EncodedHash, String base64EncodedSalt) {
        this.username = username;
        this.base64EncodedHash = base64EncodedHash;
        this.base64EncodedSalt = base64EncodedSalt;
    }

    public String getUsername() {
        return username;
    }

    public String getBase64EncodedHash() {
        return base64EncodedHash;
    }

    public String getBase64EncodedSalt() {
        return base64EncodedSalt;
    }
}