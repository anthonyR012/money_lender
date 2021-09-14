package com.anthony.moneylender.implement;

import java.io.Serializable;

public class SerializableUserImplement implements Serializable{
    private String nameUser;
    private String idUser;
    private String photoUser;
    private String email;
    private byte[] pass;

    public String getNameUser() {
        return nameUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPass() {
        return pass;
    }

    public SerializableUserImplement(String idUser, String nameUser, String photoUser, String email, byte[] pass) {
        this.nameUser = nameUser;
        this.idUser = idUser;
        this.photoUser = photoUser;
        this.email = email;
        this.pass = pass;
    }
}
