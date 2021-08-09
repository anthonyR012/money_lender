package com.anthony.moneylender.implement;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.Toast;

import com.anthony.moneylender.ui.login.optiones.fragments.forgotPass;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class SendMailImplement extends AsyncTask<Message,String,String> {
    private ProgressDialog progressDialog;
    private Context context;
    private String contextFragment;

    public void setData(Context context, Message message, String contextFragment) {
        this.context = context;
        this.contextFragment = contextFragment;
        execute(message);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Please Wait","Sending Mail..."
                ,true,false);


    }

    @Override
    protected String doInBackground(Message... messages) {
        try {

            Transport.send(messages[0]);
            return "Success";
        } catch (MessagingException e) {
            e.printStackTrace();

            return "Error";
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        if(s.equals("Success")){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
            builder.setMessage("Mail send successfully.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(contextFragment.equals("forgotPass")){
                        forgotPass instance = new forgotPass();
                        instance.updateViewEnterCode();
                    }

                }
            });
            builder.show();

        }else{

            Toast.makeText(context, "Something went wrong ? ", Toast.LENGTH_SHORT).show();
        }
    }




}
