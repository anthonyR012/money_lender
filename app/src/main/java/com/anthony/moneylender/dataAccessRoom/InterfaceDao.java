package com.anthony.moneylender.dataAccessRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

@Dao
public interface InterfaceDao {
    @Transaction
    @Query("SELECT * FROM clientes")
    public List<ClientePrestamos> getPrestamosUser();

    @Insert
    void insertAdministrator(Administrador administrador);

    @Query("SELECT COUNT(*) FROM administrador where id_administrador_pk = :code")
    public int queryIdExist(String code);

    @Query("SELECT COUNT(*) FROM administrador where email_administrador = :email")
    public int queryEmailExist(String email);

    @Query("SELECT * FROM administrador WHERE email_administrador = :email")
    public Administrador getAdministrador(String email);
}
