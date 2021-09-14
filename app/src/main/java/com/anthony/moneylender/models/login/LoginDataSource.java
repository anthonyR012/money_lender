package com.anthony.moneylender.models.login;

import static com.anthony.moneylender.implement.EncoderHelperImplement.encode;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.ThreadImplement;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private Administrador administrador;
    private boolean credential,isLoggin = false;
    private LoggedInUser objectAdministrador;
    private Exception errorCredentiales;
    private SecurityPassImplement desencriptar;
    private String datoDesencriptado;




    public Result<LoggedInUser> login(String username, String password, DataBaseMoney db) {
            // TODO: handle loggedInUser authentication

               credential = verifyCredential(username,password,db);
               if(credential != false) {
                   objectAdministrador =
                           new LoggedInUser(
                                   administrador.id_administrador_pk,
                                   administrador.getNombre_administrador()+" "+
                                           administrador.getApellido_administrador(),
                                   administrador.getDataImg_administrador()!=null?administrador.getDataImg_administrador():null,
                                   administrador.getEmail_administrador(),administrador.getPass_administrador());

                   return new Result.Success<>(objectAdministrador);

               }else{
                    errorCredentiales = new Exception("Error Login in");
                   return new Result.Error( errorCredentiales);

               }
    }



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
            desencriptar = new SecurityPassImplement();

            datoDesencriptado = desencriptar.descifra(administrador.getPass_administrador());


           if (datoDesencriptado != null && datoDesencriptado.equals(password)){
               isLoggin = true;
           }
        }
        return isLoggin;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}