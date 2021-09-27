package com.anthony.moneylender.dataAccessRoom.Entidades.Relacion;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

import java.util.List;

public class PrestamosClientes {
    @Embedded
    public Prestamos prestamos;
    @Relation(
            parentColumn = Constantes.CAMPO_CLIENTE_PRESTAMO_FK,
            entityColumn = Constantes.CAMPO_ID_CLIENTE_PK
    )
    public List<Cliente> clientes;
}
