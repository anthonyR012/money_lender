package com.anthony.moneylender.models.login.optiones;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SendMailImplement;
import com.anthony.moneylender.ui.login.optiones.fragments.forgotPass;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class forguetViewModel extends ViewModel {
    private Session session;
    private final Integer correo = R.string.email_remitente,password = R.string.pass_remitente;
    private Message message;
    private StrictMode.ThreadPolicy policy;
    private Properties properties;

    public void send(String asunto, String mensaje, EditText to, Context context, String fragmentContext){
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        try {

            session = javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication( context.getString(correo),context.getString(password));

                }
            });
            if(session!=null){


                message = new MimeMessage(session);

                message.setFrom(new InternetAddress(context.getString(correo)));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to.getText().toString().trim()));
                message.setSubject(asunto);
                message.setContent(mensaje,"text/html; charset=utf-8");
                new SendMailImplement().setData(context,message,fragmentContext);


            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

    }


}
