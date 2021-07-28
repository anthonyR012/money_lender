package com.anthony.moneylender.dataAccessRoom.Entidades.Relacion;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;

import java.util.List;

public class AdministraCliente {
    @Embedded
    public Administrador administrador;
    @Relation(
            parentColumn = Constantes.CAMPO_ID_ADMINISTRADOR_PK,
            entityColumn = Constantes.CAMPO_CLIENTE_ADMINISTRADO_FK
    )
    public List<Cliente> clientes;
}
