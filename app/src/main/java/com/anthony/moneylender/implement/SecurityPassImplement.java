package com.anthony.moneylender.implement;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityPassImplement {

    private final String sha = "SHA";
    private final String estandar = "UTF-8";
    private final String encriptation = "AES/ECB/PKCS5Padding";
    private Cipher aes;
    private String sinCifrar;
    private byte[] cifrado;


    public byte[]  cifra(String sinCifrar) {

        try {
            final byte[] bytes = sinCifrar.getBytes(estandar);
            final Cipher aes = obtieneCipher(true);
            cifrado = aes.doFinal(bytes);


        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return cifrado;
    }

    public String descifra(byte[] cifrado)  {
        try {
            final Cipher aes = obtieneCipher(false);
            final byte[] bytes = aes.doFinal(cifrado);
             sinCifrar = new String(bytes, estandar);

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return sinCifrar;
    }

    private Cipher obtieneCipher(boolean paraCifrar) {
        try {
            final String frase = "W3W3Cr1pt0fr4s3ForR3gular030821ENCRYPTATIONFRASE";
            final MessageDigest digest = MessageDigest.getInstance(sha);
            digest.update(frase.getBytes(estandar));
            final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

             aes = Cipher.getInstance(encriptation);
            if (paraCifrar) {
                aes.init(Cipher.ENCRYPT_MODE, key);
            } else {
                aes.init(Cipher.DECRYPT_MODE, key);
            }


        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return aes;
    }
}
