package com.anthony.moneylender.models.login;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String photo;
    private String email;
    private byte[] pass;

    public LoggedInUser(String userId, String displayName, String photo, String email, byte[] pass) {
        this.email = email;
        this.pass = pass;
        this.userId = userId;
        this.displayName = displayName;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPass() {
        return pass;
    }

    public String getPhoto() {
        return photo;
    }
    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}