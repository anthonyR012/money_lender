package com.anthony.moneylender.dataAccessRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.AdministraCliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

@Dao
public interface InterfaceDao {
    @Transaction
    @Query("SELECT * FROM "+ Constantes.TABLA_CLIENTES)
    public List<ClientePrestamos> getPrestamosUser();

    @Insert
    void insertAll(Cliente cliente);

    @Query("SELECT * FROM administrador WHERE email_administrador = :email" +
            " and pass_administrador = :pass ")
    public Administrador getAdministrador(String email,String pass);
}
