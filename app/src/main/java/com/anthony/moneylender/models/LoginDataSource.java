package com.anthony.moneylender.models;

import android.os.AsyncTask;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.models.login.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private Administrador administrador;
    private boolean credential,isLoggin = false;
    private LoggedInUser objectAdministrador;
    private Exception errorCredentiales;

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

    private boolean verifyCredential(String username, String password, DataBaseMoney db) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                administrador = db.interfaceDao().getAdministrador(username, password);
                if (administrador != null) {

                    isLoggin = true;
                }
            }
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isLoggin;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}