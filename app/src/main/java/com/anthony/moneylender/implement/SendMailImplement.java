package com.anthony.moneylender.implement;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.Toast;

import com.anthony.moneylender.ui.login.optiones.fragments.ForgotPass;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class SendMailImplement extends AsyncTask<Message,String,String> {
    private ProgressDialog progressDialog;
    private RepositoryImplement repositoryImplement;

    public void setData(Message message, RepositoryImplement repositoryImplement) {
        this.repositoryImplement = repositoryImplement;
        execute(message);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(repositoryImplement.getContext(), "Please Wait","Sending Mail..."
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
            AlertDialog.Builder builder = new AlertDialog.Builder(repositoryImplement.getContext());
            builder.setCancelable(false);
            builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
            builder.setMessage("Mail send successfully.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(repositoryImplement.getFragmentContext().equals("ForgotPass")){
                        ForgotPass instance = new ForgotPass();
                        instance.updateViewEnterCode(repositoryImplement);
                    }

                }
            });
            builder.show();

        }else{

            Toast.makeText(repositoryImplement.getContext(), "Something went wrong ? ", Toast.LENGTH_SHORT).show();
        }
    }




}
