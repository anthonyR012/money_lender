package com.anthony.moneylender.dataAccessRoom.Entidades.Relacion;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Historial;


import java.util.List;

public class ClienteHistorial {
    @Embedded
    public Cliente cliente;
    @Relation(
            parentColumn = Constantes.CAMPO_ID_CLIENTE_PK,
            entityColumn = Constantes.CAMPO_CLIENTE_HISTORIAL_FK
    )
    public List<Historial> historials;
}
