package com.anthony.moneylender.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String id;
    private String displayName;
    private String photo;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String photo,String id) {
        this.displayName = displayName;
        this.photo = photo;
        this.id = id;
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