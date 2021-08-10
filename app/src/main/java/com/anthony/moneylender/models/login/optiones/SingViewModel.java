package com.anthony.moneylender.models.login.optiones;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.ThreadImplement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SingViewModel extends ViewModel {
    private MutableLiveData<Integer> registroFormState = new MutableLiveData<>();
    private SecurityPassImplement encriptacion;
    private byte[] datoEncriptado;
    private int countId,countEmail;
    private int dato;
    private ThreadImplement segundoHilo;

    public int insertData(Administrador administrador, DataBaseMoney db, String passSinCifrado){

        dato =verifyState(administrador,db,passSinCifrado);
        return dato;
    }


    private int verifyState(Administrador administrador, DataBaseMoney db, String passSinCifrado){

        //quitar seteado de error en los inputs
        registroFormState = null;
        //encriptacion de contraseña, guardandola en la bd como byte
        encriptacion = new SecurityPassImplement();
        datoEncriptado = encriptacion.cifra(passSinCifrado);

        administrador.setPass_administrador(datoEncriptado);
        //ejecutar otro hilo para insertar registro
        segundoHilo = new ThreadImplement(db,administrador);
        segundoHilo.start();

        dato = R.string.complete_Insert;
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    return dato;
    }


    public MutableLiveData<Integer> getRegistroFormState() {
        return registroFormState;
    }

    public void loginDataChanged(String idUser, String nombreUser, String apellidoUser, String username, String password, DataBaseMoney db) {
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(username);
        if(registroFormState != null) {
            if (idUser.isEmpty() || idUser.length() < 3) {
                registroFormState.setValue(R.string.id_invalid);
            } else if (nombreUser.length() < 2 || nombreUser.isEmpty()) {
                registroFormState.setValue(R.string.nombre_invalid);
            } else if (apellidoUser.isEmpty() || apellidoUser.length() < 2) {
                registroFormState.setValue(R.string.apellido_invalid);
            } else if (username.isEmpty() || !mat.find()) {
                registroFormState.setValue(R.string.email_invalid);
            } else if (password.isEmpty() || password.length() < 7) {
                registroFormState.setValue(R.string.pass_invalid);
            } else {
                registroFormState.setValue(R.string.valid_action);
            }

            if (verifyNoexistDataId(db, idUser) > 0) {
                registroFormState.setValue(R.string.id_exist);
            }
            if (verifyNoexistDataEmail(db, username) > 0) {
                registroFormState.setValue(R.string.email_exist);
            }
        }
    }

    private int verifyNoexistDataEmail(DataBaseMoney db, String username) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                countEmail = db.interfaceDao().queryEmailExist(username);
            }
        });
        return countEmail;
    }

    private int verifyNoexistDataId(DataBaseMoney db, String idUser) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                countId = db.interfaceDao().queryIdExist(idUser);
            }
        });
        return countId;
    }

}
