package com.anthony.moneylender.dataAccessRoom.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;

@Entity(tableName = Constantes.TABLA_CLIENTES)
public class Cliente {
    @PrimaryKey
    @NonNull
    public String id_cliente_pk;
    @ColumnInfo(name = Constantes.CAMPO_NOMBRE_CLIENTE)
    private String nombre_cliente;
    @ColumnInfo(name = Constantes.CAMPO_APELLIDO_CLIENTE)
    private String apellido_cliente;
    @ColumnInfo(name = Constantes.CAMPO_DIRECCION_CLIENTE)
    private String direccion_cliente;
    @ColumnInfo(name = Constantes.CAMPO_TELEFONO_CLIENTE)
    private String telefono_cliente;
    @ColumnInfo(name = Constantes.CAMPO_CLIENTE_ADMINISTRADO_FK)
    private String cliente_administrado;

    public Cliente(String id_cliente_pk, String nombre_cliente, String apellido_cliente, String direccion_cliente, String telefono_cliente, String cliente_administrado) {
        this.id_cliente_pk = id_cliente_pk;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.direccion_cliente = direccion_cliente;
        this.telefono_cliente = telefono_cliente;
        this.cliente_administrado = cliente_administrado;
    }

    public String getId_cliente() {
        return id_cliente_pk;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente_pk = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getCliente_administrado() {
        return cliente_administrado;
    }

    public void setCliente_administrado(String cliente_administrado) {
        this.cliente_administrado = cliente_administrado;
    }
}
