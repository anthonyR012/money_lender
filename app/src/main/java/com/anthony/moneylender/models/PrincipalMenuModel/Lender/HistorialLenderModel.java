package com.anthony.moneylender.models.PrincipalMenuModel.Lender;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

public class HistorialLenderModel extends ViewModel {

    private DataBaseMoney db;
    private MutableLiveData<List<ClientePrestamos>> prestamos;
    private List<ClientePrestamos> lender;

    public void setDb(DataBaseMoney db){
        this.db = db;
    }


    public LiveData<List<ClientePrestamos>> getPrestamos() {
        if (prestamos == null) {
            prestamos = new MutableLiveData<List<ClientePrestamos>>();
            loadPrestamos();
        }
        return prestamos;
    }

    private void loadPrestamos() {
        // Do an asynchronous operation to fetch users.
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                lender = (db.interfaceDao().getHistorialPrestamos());
            }
        });


        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        prestamos.setValue(lender);
    }
}
