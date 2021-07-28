package com.anthony.moneylender.dataAccessRoom.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;

@Entity(tableName = Constantes.TABLA_PRESTAMOS)
public class Prestamos {
    @PrimaryKey
    private String id_prestamos_pk;
    @ColumnInfo(name = Constantes.CAMPO_CANTIDAD_PRESTAMO)
    private double cantidad_prestamo;
    @ColumnInfo(name = Constantes.CAMPO_PORCENTAJE_PRESTAMO)
    private int porcentaje_prestamo;
    @ColumnInfo(name = Constantes.CAMPO_TOTAL_PRESTAMO)
    private double total_prestamo;
    @ColumnInfo(name = Constantes.CAMPO_ESTADO_PRESTAMO)
    private String estado_prestamo;
    @ColumnInfo(name = Constantes.CAMPO_CUOTAS_PRESTAMO)
    private int cuotas_prestamo;
    @ColumnInfo(name = Constantes.CAMPO_CLIENTE_PRESTAMO_FK)
    private String clientes_prestamo_fk;
    @ColumnInfo(name = Constantes.CAMPO_HISTORIAL_PRESTAMO_FK)
    private String historial_prestamo_fk;

    public String getId_prestamos() {
        return id_prestamos_pk;
    }

    public void setId_prestamos(String id_prestamos) {
        this.id_prestamos_pk = id_prestamos;
    }

    public double getCantidad_prestamo() {
        return cantidad_prestamo;
    }

    public void setCantidad_prestamo(double cantidad_prestamo) {
        this.cantidad_prestamo = cantidad_prestamo;
    }

    public int getPorcentaje_prestamo() {
        return porcentaje_prestamo;
    }

    public void setPorcentaje_prestamo(int porcentaje_prestamo) {
        this.porcentaje_prestamo = porcentaje_prestamo;
    }

    public double getTotal_prestamo() {
        return total_prestamo;
    }

    public void setTotal_prestamo(double total_prestamo) {
        this.total_prestamo = total_prestamo;
    }

    public String getEstado_prestamo() {
        return estado_prestamo;
    }

    public void setEstado_prestamo(String estado_prestamo) {
        this.estado_prestamo = estado_prestamo;
    }

    public int getCuotas_prestamo() {
        return cuotas_prestamo;
    }

    public void setCuotas_prestamo(int cuotas_prestamo) {
        this.cuotas_prestamo = cuotas_prestamo;
    }

    public String getClientes_prestamo_fk() {
        return clientes_prestamo_fk;
    }

    public void setClientes_prestamo_fk(String clientes_prestamo_fk) {
        this.clientes_prestamo_fk = clientes_prestamo_fk;
    }

    public String getHistorial_prestamo_fk() {
        return historial_prestamo_fk;
    }

    public void setHistorial_prestamo_fk(String historial_prestamo_fk) {
        this.historial_prestamo_fk = historial_prestamo_fk;
    }
}
