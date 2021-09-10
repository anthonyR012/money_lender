package com.anthony.moneylender.models.PrincipalMenuModel;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.models.login.LoginRepository;

import java.util.ArrayList;
import java.util.List;


public class RegisterNewLender  extends ViewModel {

    private MutableLiveData<List<Cliente>> cliente = new MutableLiveData<>();
    private List<Cliente> clientSame;
    public void ClientDataChanged(DataBaseMoney db,String name){
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                clientSame = db.interfaceDao().getClient(name);
//
//            }
//        });
//



    }
    public void insertNewLender(Prestamos prestamos, DataBaseMoney db){

        insertNewLenderPrivate(prestamos,db);

    }



    public LiveData<List<Cliente>> getClientResult() {
        return cliente;
    }

    private void insertNewLenderPrivate(Prestamos prestamos, DataBaseMoney db) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.interfaceDao().insertLender(prestamos);
            }
        });
    }

}
