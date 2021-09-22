package com.anthony.moneylender.models.PrincipalMenuModel.Client;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

public class HistorialClientModel extends ViewModel {

    private DataBaseMoney db;
    private MutableLiveData<List<ClientePrestamos>> prestamos;
    private List<ClientePrestamos> lender;

    public void setDb(DataBaseMoney db){
        this.db = db;
    }
}
