package com.anthony.moneylender.ui.login.optiones.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anthony.moneylender.R;
import com.anthony.moneylender.models.login.optiones.forguetViewModel;


public class forgotPass extends Fragment {


    private EditText to;
    private String mensaje = "Su numero de recuperacion es: ";
    private int numeroRandom;
    private final String asunto = "RESTABLECER CONTRASEÑA";
    private Button enviar;
    private Context context;
    private forguetViewModel viewModel;
    private String fragmentContext;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        context = getContext();
        fragmentContext = this.getClass().getSimpleName();
        to = root.findViewById(R.id.remitente);
        enviar = root.findViewById(R.id.btn_enviar);

        viewModel = new ViewModelProvider(this).get(forguetViewModel.class);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailMessageSend();
            }
        });


        return root;

    }

    private void mailMessageSend() {
        mensaje+= String.valueOf(createNumberRandom());
        viewModel.send(asunto,mensaje,to,context,fragmentContext);

    }

    private int createNumberRandom() {

        int valor1 = (int) (Math.random()*1000);
        int valor2 = (int) (Math.random()*1000);
        numeroRandom = valor1 * valor2;
        return numeroRandom;
    }

    public void updateViewEnterCode() {
        
    }



}