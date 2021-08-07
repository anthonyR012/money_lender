package com.anthony.moneylender.implement;

import java.io.Serializable;

public class SerializableUserImplement implements Serializable{
    private String nameUser;
    private String idUser;
    private String photoUser;

    public String getNameUser() {
        return nameUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public SerializableUserImplement(String idUser, String nameUser, String photoUser) {
        this.nameUser = nameUser;
        this.idUser = idUser;
        this.photoUser = photoUser;
    }
}
