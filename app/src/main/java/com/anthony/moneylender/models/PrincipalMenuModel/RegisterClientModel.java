package com.anthony.moneylender.models.PrincipalMenuModel;

import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;

public class RegisterClientModel extends ViewModel {

    public void insertData(Cliente client, DataBaseMoney db){

        insertDataPrivate(client,db);

    }

    private void insertDataPrivate(Cliente client, DataBaseMoney db) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.interfaceDao().insertClient(client);
            }
        });

    }

}
