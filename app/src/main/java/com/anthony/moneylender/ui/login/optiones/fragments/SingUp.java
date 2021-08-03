package com.anthony.moneylender.ui.login.optiones.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.models.login.optiones.singViewModel;
import com.anthony.moneylender.ui.login.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingUp extends Fragment {
    private singViewModel viewModel;
    private Context context;
    private DataBaseMoney db;
    private Administrador createAdministrador;
    private EditText id,nombre,apellido,email,pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = new ViewModelProvider(this).get(singViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sing_up, container, false);
        context = getActivity().getApplicationContext();
        db = DataBaseMoney.getInstance(context);
        id = root.findViewById(R.id.idUser);
        nombre = root.findViewById(R.id.nameUser);
        apellido = root.findViewById(R.id.lastNameUser);
        email = root.findViewById(R.id.emailUser);
        pass = root.findViewById(R.id.passUser);


            root.findViewById(R.id.singIn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }
            });

        root.findViewById(R.id.btn_registrar).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                registraUser();
            }
        });

        viewModel.getRegistroFormState().observe( getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stateRegistro) {
                if (stateRegistro == null) {
                    return;
                }
                if (stateRegistro.equals(R.string.id_invalid)) {
                    id.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.nombre_invalid)) {
                    nombre.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.apellido_invalid)) {
                    apellido.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.email_invalid)) {
                    email.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.pass_invalid)) {
                    pass.setError(getString(stateRegistro));
                }
                if(stateRegistro.equals(R.string.valid_action)){
                    root.findViewById(R.id.btn_registrar).setEnabled(true);
                }

            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore

            }

            @Override
            public void afterTextChanged(Editable s) {

                viewModel.loginDataChanged(id.getText().toString(),nombre.getText().toString(),apellido
                        .getText().toString(),email.getText().toString(),pass.getText().toString());
            }
        };
        id.addTextChangedListener(afterTextChangedListener);
        nombre.addTextChangedListener(afterTextChangedListener);
        apellido.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);
        pass.addTextChangedListener(afterTextChangedListener);

        return root;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registraUser() {

        createAdministrador = new Administrador(id.getText().toString(),nombre.getText().toString(),
                apellido.getText().toString(),email.getText().toString(),null);

        viewModel.insertData(createAdministrador,db,pass.getText().toString());

    }


}