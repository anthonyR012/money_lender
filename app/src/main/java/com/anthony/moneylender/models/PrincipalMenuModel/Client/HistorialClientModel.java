package com.anthony.moneylender.models.PrincipalMenuModel.Client;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

public class HistorialClientModel extends ViewModel {

    private DataBaseMoney db;
    private MutableLiveData<List<ClientePrestamos>> clientes;
    private List<ClientePrestamos> lender;

    public void setDb(DataBaseMoney db){
        this.db = db;
    }


    public MutableLiveData<List<ClientePrestamos>> getPrestamos() {
        if (clientes == null) {
            clientes = new MutableLiveData<List<ClientePrestamos>>();
            loadClient();
        }
        return clientes;
    }

    private void loadClient() {
        // Do an asynchronous operation to fetch users.
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                lender = (db.interfaceDao().getClientes());
            }
        });


        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientes.setValue(lender);
    }
}
