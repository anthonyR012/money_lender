package com.anthony.moneylender.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String id;
    private String displayName;
    private String photo;
    private String email;
    private byte[] pass;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String photo, String id, String email, byte[] pass) {
        this.email = email;
        this.pass = pass;
        this.displayName = displayName;
        this.photo = photo;
        this.id = id;
    }

    String getDisplayEmail() {
        return email;
    }

    byte[] getDisplayPass() {
        return pass;
    }

    String getDisplayId() {
        return id;
    }
    String getDisplayName() {
        return displayName;
    }

    String getDisplayPhoto() {
        return photo;
    }
}