package com.anthony.moneylender.dataAccessRoom.Entidades.Relacion;


import androidx.room.Embedded;
import androidx.room.Relation;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Historial;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

import java.util.List;

public class PrestamosHistorial {
    @Embedded
    public Historial historial;
    @Relation(
            parentColumn = Constantes.CAMPO_ID_HISTORIAL_PK,
            entityColumn = Constantes.CAMPO_HISTORIAL_PRESTAMO_FK
    )
    public List<Prestamos> prestamos;
}
