package com.anthony.moneylender.ui.PrincipalMenu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Fade;
import android.view.animation.DecelerateInterpolator;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.AcercaFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client.HistorialClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender.HistorialLenderFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.InicioFragmentMenu;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PerfilAmdFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PrestamoClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.RegistrarClientFragment;

public class PrincipalMenu extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);


    }


    @Override
    public void onBackPressed() {

        //CUANDO SE PRECIONE ATRAS, INSTANCIA BULDER QUE PREGUNTA SI DESEA SALIR DE APLICACIÓN
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalMenu.this);
                builder.setMessage("¿Deseas salir de la aplicación?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProgressDialog progressDialog = ProgressDialog.show(PrincipalMenu.this, "Thank's :)","Closing..."
                                        ,true,false);
                                new CountDownTimer(1500, 1000) {

                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        Intent intent = new Intent(Intent.ACTION_MAIN);
                                        intent.addCategory(Intent.CATEGORY_HOME);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        finish();
                                        startActivity(intent);
                                    }
                                }.start();


                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                builder.show();

    }



}