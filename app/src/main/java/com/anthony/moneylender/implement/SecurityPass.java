package com.anthony.moneylender.implement;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityPass {
    private SecretKeySpec secretKeyEncript,secretKeyDescript;
    private SecretKeySpec secret;
    private final String sha = "SHA-256";
    private final String estandar = "UTF-8";
    private final String encriptation = "AES";
    private String datosEncriptadoString,datosDesencriptadoString;
    private byte[] datosEncriptadorByte,key,datosDesencriptadoByte,datosDescodificados;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encriptar(String datos, String passWord){
        try {
            secretKeyEncript = generateKey(passWord);
            Cipher cipher = Cipher.getInstance(encriptation);
            cipher.init(Cipher.ENCRYPT_MODE,secretKeyEncript);
            datosEncriptadorByte = cipher.doFinal(datos.getBytes());
            datosEncriptadoString = Base64.getEncoder().encodeToString(datosEncriptadorByte);
        }catch (Exception e){
            e.getMessage();
        }
            return datosEncriptadoString;
    }
    private SecretKeySpec generateKey(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance(sha);
            key = password.getBytes(estandar);
            key = digest.digest(key);
            secret = new SecretKeySpec(key,encriptation);
        }catch (Exception e){
            e.getMessage();
        }

        return secret;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String desencriptar(String datos, String passWord){
        try{
            secretKeyDescript = generateKey(passWord);
            Cipher cipher = Cipher.getInstance(encriptation);
            cipher.init(Cipher.DECRYPT_MODE,secretKeyDescript);

//            datosDesencriptadoString = new String(datosDesencriptadoByte);


        }catch (Exception e){
            Log.i("Exception","cons ");
          e.printStackTrace();
            e.getMessage();
        }
        return datosDesencriptadoString;
    }
}
