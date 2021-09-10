package com.anthony.moneylender.ui.login.optiones.fragments;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.databinding.FragmentForgotPassBinding;
import com.anthony.moneylender.databinding.FragmentSingUpBinding;
import com.anthony.moneylender.implement.RepositoryImplement;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.models.login.optiones.ForguetViewModel;
import com.anthony.moneylender.ui.login.LoginActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;


public class ForgotPass extends Fragment {

    private FragmentForgotPassBinding binding;
    private String mensaje = "Su numero de recuperacion es: ";
    private int numeroRandom;
    private Context context;
    private ForguetViewModel viewModel;
    private String fragmentContext;
    private View root;
    private ViewGroup containerLayout;
    private DataBaseMoney db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        containerLayout = container;
        binding = FragmentForgotPassBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        context = getContext();
        fragmentContext = this.getClass().getSimpleName();
        db = DataBaseMoney.getInstance(context);


        viewModel = new ViewModelProvider(this).get(ForguetViewModel.class);

        binding.btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactPersonal();
            }
        });
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoginActivity.class));
            }
        });

        binding.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailMessageSend();
            }
        });


        return root;

    }

    private void ContactPersonal() {

    }

    private void mailMessageSend() {

        //CONFIRMAR CORREO EXISTENTE EN LA BBDD
        if(viewModel.veryfyStateEmail(db,binding.remitente.getText().toString()) > 0){
            //DEVUELVE NUMERO RANDOM A STRING
            int number = createNumberRandom();
            mensaje+= number;
            //GUARDA DATOS DE LA VISTA
            RepositoryImplement repositoryImplement = new
                    RepositoryImplement
                    (containerLayout,root,fragmentContext,context,binding.remitente.getText().toString(),mensaje,number,db);
            //INSTANCIA OBJETO MENSAJE Y LO ENVIA
            viewModel.send(repositoryImplement);
        }else{
            Snackbar mySnackbar = Snackbar.make(root,
                    "Correo no registrado",
                    BaseTransientBottomBar.LENGTH_LONG
                    );
            mySnackbar.show();
        }



    }

    private int createNumberRandom() {

        int valor1 = (int) (Math.random()*1000);
        int valor2 = (int) (Math.random()*1000);
        numeroRandom = valor1 * valor2;
        return numeroRandom;
    }

    public void updateViewEnterCode(RepositoryImplement repositoryImplement) {

            //INICIALIZACION VARIABLES
            EditText introduceCode = repositoryImplement.getRoot().findViewById(R.id.remitente);
            Button verify = repositoryImplement.getRoot().findViewById(R.id.btn_enviar);
            TextView text = repositoryImplement.getRoot().findViewById(R.id.information);
            TextView reenviar = repositoryImplement.getRoot().findViewById(R.id.btn_ingresar);
            TextView wrongEmail = repositoryImplement.getRoot().findViewById(R.id.btn_contact);

            introduceCode.setInputType(TYPE_CLASS_NUMBER);
            //SETEA DATOS DE LA VISTA
            introduceCode.setText("");
            introduceCode.setHint("Ingrese el codigo");
            reenviar.setText("Reenviar Codigo");
            verify.setText("Verificar");
            text.setText("Ingresa el codigo que te enviamos al correo");
            wrongEmail.setText("Correo equivocado");
            //EVENTOS CLICK VISTA CREADA
            reenviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reenviarMessage(repositoryImplement);
                }
            });

        wrongEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarActivity(repositoryImplement.getContext());
            }
        });
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmCodeIntroduced(introduceCode.getText().toString(),repositoryImplement);
                }
            });


    }


    private void confirmCodeIntroduced(String code, RepositoryImplement repo) {

        if(code.equals(( String.valueOf(repo.getNumber())))){
            UpdateViewForNewPass(repo);

        }else{
            Snackbar mySnackbar = Snackbar.make(repo.getRoot(),
                    "Codigo incorrecto",
                    BaseTransientBottomBar.LENGTH_LONG
            );
            mySnackbar.show();
        }
    }


    private void UpdateViewForNewPass(RepositoryImplement repo) {
        EditText introducePass = repo.getRoot().findViewById(R.id.remitente);
        Button update = repo.getRoot().findViewById(R.id.btn_enviar);
        TextView text = repo.getRoot().findViewById(R.id.information);
        TextView reenviar = repo.getRoot().findViewById(R.id.btn_ingresar);
        TextView wrongEmail = repo.getRoot().findViewById(R.id.btn_contact);

        introducePass.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);

        introducePass.setText("");
        introducePass.setHint("Ingrese nueva contraseña");
        update.setText("Actualizar");
        text.setText("Proceso exitoso, Ingresa tu nueva contraseña");

        wrongEmail.setVisibility(View.GONE);
        reenviar.setVisibility(View.GONE);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassInDb(introducePass.getText().toString(),repo);
            }
        });

    }

    private void updatePassInDb(String pass, RepositoryImplement repo) {
        ForguetViewModel viewModel = new ForguetViewModel();

        viewModel.updatePass(repo.getDb(),pass,repo.getTo());

//finalizamos la actividad actual

        AlertDialog.Builder builder = new AlertDialog.Builder(repo.getContext());
        builder.setMessage(R.string.update_complete)
                .setPositiveButton(R.string.accept_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setClass(repo.getContext(),LoginActivity.class);
                        //llamamos a la actividad
                        repo.getContext().startActivity(intent);
                    }
                });
        builder.show();

    }

    private void reiniciarActivity(Context context) {

        Intent intent=new Intent();
        intent.setClass(context, context.getClass());
        //llamamos a la actividad
        context.startActivity(intent);
        //finalizamos la actividad actual


    }

    private void reenviarMessage(RepositoryImplement repositoryImplement) {
       ForguetViewModel viewModel = new ForguetViewModel();

        viewModel.send(repositoryImplement);

    }


}