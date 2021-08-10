package com.anthony.moneylender.ui.login.optiones.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
import com.anthony.moneylender.implement.RepositoryImplement;
import com.anthony.moneylender.models.login.optiones.ForguetViewModel;
import com.anthony.moneylender.ui.login.LoginActivity;


public class ForgotPass extends Fragment {


    private EditText to;
    private TextView ingresar,contactar;
    private String mensaje = "Su numero de recuperacion es: ";
    private int numeroRandom;
    private Button enviar;
    private Context context;
    private ForguetViewModel viewModel;
    private String fragmentContext;
    private View root;
    private ViewGroup containerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        containerLayout = container;
        root = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        context = getContext();
        fragmentContext = this.getClass().getSimpleName();
        to = root.findViewById(R.id.remitente);
        enviar = root.findViewById(R.id.btn_enviar);
        ingresar = root.findViewById(R.id.btn_ingresar);
        contactar = root.findViewById(R.id.btn_contact);

        viewModel = new ViewModelProvider(this).get(ForguetViewModel.class);

        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactPersonal();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoginActivity.class));
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
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



        //DEVUELVE NUMERO RANDOM A STRING
        int number = createNumberRandom();
        mensaje+= number;
        //GUARDA DATOS DE LA VISTA
        RepositoryImplement repositoryImplement = new
                RepositoryImplement(containerLayout,root,fragmentContext,context,to.getText().toString(),mensaje,number);
        //INSTANCIA OBJETO MENSAJE Y LO ENVIA
        viewModel.send(repositoryImplement);

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
                    confirmCodeIntroduced(introduceCode.getText().toString(),repositoryImplement.getNumber());
                }
            });


    }

    private void confirmCodeIntroduced(String code, int number) {
        Log.i("Code ingresado",code);
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