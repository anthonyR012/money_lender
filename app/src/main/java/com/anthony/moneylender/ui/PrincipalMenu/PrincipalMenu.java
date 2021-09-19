package com.anthony.moneylender.ui.PrincipalMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.AcercaFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client.HistorialClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender.HistorialLenderFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.InicioFragmentMenu;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PerfilAmdFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PrestamoClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.RegistrarClientFragment;

public class PrincipalMenu extends AppCompatActivity implements IcomunicaFragments {
    private  Fragment initialFragment,administrarFragment,
            registrarFragment,acercaFragment,perfilFragment
            ,historialFragment,prestamoFragment;
    private SerializableUserImplement administrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);

        Bundle bundle = getIntent().getExtras();
        administrador = (SerializableUserImplement) bundle.getSerializable("INFORMATION");

        Bundle argsAdministrator = new Bundle();
        argsAdministrator.putSerializable("INFORMATION",administrador);
        administrarFragment = new HistorialClientFragment();
         initialFragment = new InicioFragmentMenu();
         registrarFragment = new RegistrarClientFragment();
         acercaFragment = new AcercaFragment();
        perfilFragment = new PerfilAmdFragment();
         historialFragment = new HistorialLenderFragment();
         prestamoFragment = new PrestamoClientFragment();

        //SEND DATA ADMINISTRADOR WITH CLASS SERIALIZABLE
        registrarFragment.setArguments(argsAdministrator);
         initialFragment.setArguments(argsAdministrator);
        perfilFragment.setArguments(argsAdministrator);
        acercaFragment.setArguments(argsAdministrator);

         getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
         ,initialFragment).commit();

    }


    @Override
    public void onBackPressed() {
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



    @Override
    public void RegistrarClient() {

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,registrarFragment).commit();

    }

    @Override
    public void AdministrarClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,administrarFragment).commit();
    }

    @Override
    public void PerfilAdm() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,perfilFragment).commit();
    }

    @Override
    public void PrestamoClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,prestamoFragment).commit();
    }

    @Override
    public void Acerca() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,acercaFragment).commit();
    }

    @Override
    public void Historial() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,historialFragment).commit();
    }

    @Override
    public void inicio() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,initialFragment).commit();
    }
}