package com.anthony.moneylender.models.login.optiones;

import android.os.StrictMode;

import androidx.lifecycle.ViewModel;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.RepositoryImplement;
import com.anthony.moneylender.implement.SendMailImplement;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForguetViewModel extends ViewModel {
    private Session session;
    private final Integer correo = R.string.email_remitente,password = R.string.pass_remitente;
    private final String asunto = "RESTABLECER CONTRASEÑA";
    private Message message;
    private StrictMode.ThreadPolicy policy;
    private Properties properties;

    public void send(RepositoryImplement repositoryImplement){
        //POLITICA Y PROPIEDADES DEL MENSAJE
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        try {
            //AUTENTICAR CORREO EMISOR
            session = javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication( repositoryImplement.getContext().getString(correo), repositoryImplement.getContext().getString(password));

                }
            });
            if(session!=null){

                //GENERAR MENSAJE
                message = new MimeMessage(session);

                message.setFrom(new InternetAddress(repositoryImplement.getContext().getString(correo)));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(repositoryImplement.getTo()));
                message.setSubject(asunto);
                message.setContent(repositoryImplement.getMensaje(),"text/html; charset=utf-8");
                //LLAMAR METODO CON PROGRESS
                new SendMailImplement().setData(message, repositoryImplement);


            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

    }



}
