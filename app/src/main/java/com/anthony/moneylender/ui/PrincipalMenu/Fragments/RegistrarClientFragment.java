package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.databinding.FragmentForgotPassBinding;
import com.anthony.moneylender.databinding.FragmentRegistrarClientBinding;
import com.anthony.moneylender.implement.MySnackbar;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterClientModel;
import com.anthony.moneylender.models.login.optiones.SingViewModel;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class RegistrarClientFragment extends Fragment {
    private FragmentRegistrarClientBinding binding;
    private RegisterClientModel viewModel;
    private Cliente client;
    private View root;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    private DataBaseMoney db;
    private FloatingActionButton return_,administra_,about_;
    private Snackbar mySnackbar;
    private SerializableUserImplement user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegistrarClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RegisterClientModel.class);
        db = DataBaseMoney.getInstance(getContext());

        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);



        user = (SerializableUserImplement) getArguments().getSerializable("INFORMATION");

        eventClick();

        return root;
    }

    private void eventClick() {
        binding.btnCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new Cliente(binding.nameClient.getText().toString(),binding.lastClient.getText().toString(),
                        binding.addressClient.getText().toString(),binding.phoneClient.getText().toString(),user.getIdUser());
                viewModel.insertData(client,db);

                MySnackbar mySnackba = new MySnackbar(getString(R.string.complete_Insert),root);


            }
        });

        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfacesFragment.inicio();
            }
        });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfacesFragment.AdministrarClient();
            }
        });
        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfacesFragment.Acerca();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            interfacesFragment = (IcomunicaFragments) activity;
        }
    }

}