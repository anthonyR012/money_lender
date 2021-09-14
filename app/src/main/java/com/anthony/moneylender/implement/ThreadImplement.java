package com.anthony.moneylender.implement;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

public class ThreadImplement extends Thread{
    private DataBaseMoney db;
    private Administrador administrador;



    public ThreadImplement(DataBaseMoney db, Administrador administrador) {
        this.administrador = administrador;
        this.db = db;
    }



    @Override
    public void run() {
        super.run();
                db.interfaceDao().insertAdministrator(administrador);

    }




}
