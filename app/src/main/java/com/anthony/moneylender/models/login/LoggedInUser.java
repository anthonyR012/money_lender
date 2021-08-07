package com.anthony.moneylender.models.login;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String photo;


    public LoggedInUser(String userId, String displayName, String photo) {
        this.userId = userId;
        this.displayName = displayName;
        this.photo = photo;
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