package com.anthony.moneylender.dataAccessRoom.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;

@Entity(tableName = Constantes.TABLA_ADMINISTRADOR)
public class Administrador {
    @PrimaryKey
    @NonNull
    public String id_administrador_pk;
    @ColumnInfo(name = Constantes.CAMPO_NOMBRE_ADMINISTRADOR)
    private String nombre_administrador;
    @ColumnInfo(name = Constantes.CAMPO_APELLIDO_ADMINISTRADOR)
    private String apellido_administrador;
    @ColumnInfo(name = Constantes.CAMPO_EMAIL_ADMINISTRADOR)
    private String email_administrador;
    @ColumnInfo(name = Constantes.CAMPO_PASS_ADMINISTRADOR)
    private String pass_administrador;

    public Administrador(@NonNull String id_administrador_pk, String nombre_administrador, String apellido_administrador, String email_administrador, String pass_administrador) {
        this.id_administrador_pk = id_administrador_pk;
        this.nombre_administrador = nombre_administrador;
        this.apellido_administrador = apellido_administrador;
        this.email_administrador = email_administrador;
        this.pass_administrador = pass_administrador;
    }

    public String getId_administrador() {
        return id_administrador_pk;
    }



    public void setId_administrador(String id_administrador) {
        this.id_administrador_pk = id_administrador;
    }

    public String getNombre_administrador() {
        return nombre_administrador;
    }

    public void setNombre_administrador(String nombre_administrador) {
        this.nombre_administrador = nombre_administrador;
    }

    public String getApellido_administrador() {
        return apellido_administrador;
    }

    public void setApellido_administrador(String apellido_administrador) {
        this.apellido_administrador = apellido_administrador;
    }

    public String getEmail_administrador() {
        return email_administrador;
    }

    public void setEmail_administrador(String email_administrador) {
        this.email_administrador = email_administrador;
    }

    public String getPass_administrador() {
        return pass_administrador;
    }

    public void setPass_administrador(String pass_administrador) {
        this.pass_administrador = pass_administrador;
    }
}
