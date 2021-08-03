package com.anthony.moneylender.models.login;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.implement.SecurityPass;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private Administrador administrador;
    private boolean credential,isLoggin = false;
    private LoggedInUser objectAdministrador;
    private Exception errorCredentiales;
    private SecurityPass desencriptar;
    private String datoDesencriptado;
    private final String keyEncription = "wewe";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Result<LoggedInUser> login(String username, String password, DataBaseMoney db) {
            // TODO: handle loggedInUser authentication

               credential = verifyCredential(username,password,db);
               if(credential != false) {
                   objectAdministrador =
                           new LoggedInUser(
                                   administrador.id_administrador_pk,
                                   administrador.getNombre_administrador()+" "+administrador.getApellido_administrador());
                   return new Result.Success<>(objectAdministrador);

               }else{

                   return new Result.Error( errorCredentiales = new Exception("Error logging in"));

               }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean verifyCredential(String username, String password, DataBaseMoney db) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                administrador = db.interfaceDao().getAdministrador(username);

            }
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (administrador != null) {
            desencriptar = new SecurityPass();

            datoDesencriptado = desencriptar.desencriptar(keyEncription,password);


           if (datoDesencriptado.equals(administrador.getPass_administrador())){
               isLoggin = true;
           }
        }
        return isLoggin;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}