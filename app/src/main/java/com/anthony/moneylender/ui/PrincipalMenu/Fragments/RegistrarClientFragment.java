package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.databinding.FragmentRegistrarClientBinding;
import com.anthony.moneylender.implement.MySnackbarImplement;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterClientModel;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class RegistrarClientFragment extends Fragment {
    private FragmentRegistrarClientBinding binding;
    private RegisterClientModel viewModel;
    private Cliente client;
    private View root;
    private DataBaseMoney db;
    private FloatingActionButton return_,administra_,about_;
    private Snackbar mySnackbar;
    private SerializableUserImplement administrador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegistrarClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RegisterClientModel.class);
        db = DataBaseMoney.getInstance(getContext());



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);

        Bundle bundle = getActivity().getIntent().getExtras();

        administrador = (SerializableUserImplement) bundle.getSerializable("INFORMATION");
        final NavController navController = Navigation.findNavController(view);

        eventsClick(navController);

    }

    private void eventsClick(NavController navController) {

        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registrarClientFragment_to_inicioFragmentMenu);
            }
        });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registrarClientFragment_to_historialClientFragment3);
            }
        });

        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registrarClientFragment_to_acercaFragment);
            }
        });
        binding.btnCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new Cliente(binding.nameClient.getText().toString(),binding.lastClient.getText().toString(),
                        binding.addressClient.getText().toString(),binding.phoneClient.getText().toString(),administrador.getIdUser());
                viewModel.insertData(client,db);

                MySnackbarImplement mySnackba = new MySnackbarImplement(getString(R.string.complete_Insert),root);


            }
        });


    }



}