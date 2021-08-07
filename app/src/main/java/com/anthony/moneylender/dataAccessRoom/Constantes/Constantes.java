package com.anthony.moneylender.dataAccessRoom.Constantes;

public class Constantes {

    public static final String BD = "money";
    //    CONSTANTES TABLA CLIENTE
    public static final String TABLA_CLIENTES = "clientes";
    public static final String CAMPO_ID_CLIENTE_PK = "id_cliente_pk";
    public static final String CAMPO_NOMBRE_CLIENTE = "nombre_cliente";
    public static final String CAMPO_APELLIDO_CLIENTE = "apellido_cliente";
    public static final String CAMPO_DIRECCION_CLIENTE = "direccion_cliente";
    public static final String CAMPO_TELEFONO_CLIENTE = "telefono_cliente";
    public static final String CAMPO_CLIENTE_ADMINISTRADO_FK = "cliente_administrado_fk";
    //    CONSTANTES TABLA ADMINISTRADOR
    public static final String TABLA_ADMINISTRADOR = "administrador";
    public static final String CAMPO_ID_ADMINISTRADOR_PK = "id_administrador_pk";
    public static final String CAMPO_NOMBRE_ADMINISTRADOR = "nombre_administrador";
    public static final String CAMPO_APELLIDO_ADMINISTRADOR = "apellido_administrador";
    public static final String CAMPO_EMAIL_ADMINISTRADOR = "email_administrador";
    public static final String CAMPO_PASS_ADMINISTRADOR = "pass_administrador";
    public static final String CAMPO_PHOTO_ADMINISTRADOR = "photo_administrador";
    //    CONSTANTES TABLA PRESTAMOS
    public static final String TABLA_PRESTAMOS = "prestamos";
    public static final String CAMPO_ID_PRESTAMOS_PK = "id_prestamos_pk";
    public static final String CAMPO_CANTIDAD_PRESTAMO = "cantidad_prestamos";
    public static final String CAMPO_PORCENTAJE_PRESTAMO = "porcentajes_prestamos";
    public static final String CAMPO_TOTAL_PRESTAMO = "total_prestamos";
    public static final String CAMPO_ESTADO_PRESTAMO = "estado_prestamos";
    public static final String CAMPO_CUOTAS_PRESTAMO = "cuotas_prestamos";
    public static final String CAMPO_CLIENTE_PRESTAMO_FK = "cliente_prestamos_fk";
    public static final String CAMPO_HISTORIAL_PRESTAMO_FK = "historial_prestamos_fk";
//    CONSTANTES TABLA HISTORIAL PRESTAMOS
    public static final String TABLA_HISTORIAL = "historial_prestamos";
    public static final String CAMPO_ID_HISTORIAL_PK = "id_historial_pk";
    public static final String CAMPO_CLIENTE_HISTORIAL_FK = "cliente_historial_fk";

}
