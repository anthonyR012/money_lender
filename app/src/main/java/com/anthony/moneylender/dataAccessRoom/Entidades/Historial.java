package com.anthony.moneylender.dataAccessRoom.Entidades;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;

@Entity(tableName = Constantes.TABLA_HISTORIAL)
public class Historial {
    @PrimaryKey
    private String id_historial_pk;
    @ColumnInfo(name = Constantes.CAMPO_TOTAL_PRESTAMO)
    private double total_prestamo_historial;
    @ColumnInfo(name = Constantes.CAMPO_CLIENTE_HISTORIAL_FK)
    private String clientes_historial_fk;

    public String getId_historial_pk() {
        return id_historial_pk;
    }

    public void setId_historial_pk(String id_historial_pk) {
        this.id_historial_pk = id_historial_pk;
    }

    public double getTotal_prestamo_historial() {
        return total_prestamo_historial;
    }

    public void setTotal_prestamo_historial(double total_prestamo_historial) {
        this.total_prestamo_historial = total_prestamo_historial;
    }

    public String getClientes_historial_fk() {
        return clientes_historial_fk;
    }

    public void setClientes_historial_fk(String clientes_historial_fk) {
        this.clientes_historial_fk = clientes_historial_fk;
    }
}
