package com.anthony.moneylender.implement;

import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

import java.util.List;


    public class OrderDataImplement{
        private int cantidadPrestamo;
        private String fechaInit;
        private String fechaFin;
        private String clientePrestamo;
        private String estado;



        public OrderDataImplement(int cantidadPrestamo, String fechaInit, String fechaFin, String clientePrestamo, String estado) {
            this.cantidadPrestamo = cantidadPrestamo;
            this.fechaInit = fechaInit;
            this.fechaFin = fechaFin;
            this.clientePrestamo = clientePrestamo;
            this.estado = estado;
        }
        public String getEstado() {
            return estado;
        }
        public int getCantidadPrestamo() {
            return cantidadPrestamo;
        }

        public String getFechaInit() {
            return fechaInit;
        }

        public String getFechaFin() {
            return fechaFin;
        }

        public String getClientePrestamo() {
            return clientePrestamo;
        }
    }

