package com.anthony.moneylender.models.login.optiones;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.implement.SecurityPass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class singViewModel extends ViewModel {
    private MutableLiveData<Integer> registroFormState = new MutableLiveData<>();
    private final String keyEncription = "wewe";
    private SecurityPass encriptacion;
    private String datoEncriptado;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertData(Administrador administrador, DataBaseMoney db){
        verifyState(administrador,db);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void verifyState(Administrador administrador, DataBaseMoney db){
        encriptacion = new SecurityPass();
        datoEncriptado = encriptacion.encriptar(keyEncription,administrador.getPass_administrador());
        Log.i("dato encriptado","este "+datoEncriptado.toString());
        administrador.setPass_administrador(datoEncriptado);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.interfaceDao().insertAdministrator(administrador);
            }
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public MutableLiveData<Integer> getRegistroFormState() {
        return registroFormState;
    }

    public void loginDataChanged(String idUser, String nombreUser, String apellidoUser, String username, String password) {
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(username);

        if(idUser.isEmpty() || idUser.length() < 3){
            registroFormState.setValue(R.string.id_invalid);
        }else if(nombreUser.length() < 2 || nombreUser.isEmpty()){
            registroFormState.setValue(R.string.nombre_invalid);
        }else if(apellidoUser.isEmpty() || apellidoUser.length() < 2){
            registroFormState.setValue(R.string.apellido_invalid);
        }else if(username.isEmpty() || !mat.find()){
            registroFormState.setValue(R.string.email_invalid);
        }else if(password.isEmpty() || password.length() < 7){
            registroFormState.setValue(R.string.pass_invalid);
        }else{
            registroFormState.setValue(R.string.valid_action);
        }


    }


}
