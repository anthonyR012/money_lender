package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import static com.anthony.moneylender.implement.EncoderHelperImplement.decode;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.databinding.FragmentAcercaBinding;
import com.anthony.moneylender.databinding.FragmentInicioMenuBinding;
import com.anthony.moneylender.implement.SerializableUserImplement;

import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client.HistorialClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender.HistorialLenderFragment;
import com.anthony.moneylender.ui.PrincipalMenu.PrincipalMenu;
import com.anthony.moneylender.ui.login.LoginActivity;

public class InicioFragmentMenu extends Fragment {


    private Bitmap photo;
    private FragmentInicioMenuBinding binding;
    private ImageView imagenAdapter;
    private View root;
    private SerializableUserImplement administrador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentInicioMenuBinding.inflate(inflater, container, false);
        root = binding.getRoot();


        return root;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RECIBE BUDLE QUE ENVIAN DESDE LOGIN, CON DATOS DE ADMINISTRADOR
        Bundle bundle = getActivity().getIntent().getExtras();
        administrador = (SerializableUserImplement) bundle.getSerializable("INFORMATION");
        //INSERTE IMAGEN DE PERFIL SI EXISTE
        if(administrador.getPhotoUser() != null){
            photo = decode(administrador.getPhotoUser());
            imagenAdapter = root.findViewById(R.id.photoUserSingin);
            imagenAdapter.setImageBitmap(photo);

        }
        final NavController navController = Navigation.findNavController(view);
        eventsClick(navController);
    }

    private void eventsClick(NavController navController) {

        binding.linearRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_registrarClientFragment);
            }
        });

        binding.linearPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_historialClientFragment);

            }
        });

        binding.linearPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_perfilAmdFragment2);
            }
        });
        binding.linearLender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_prestamoClientFragment);
            }
        });
        binding.linearSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_acercaFragment);
            }
        });


        binding.linearHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_inicioFragmentMenu_to_historialLenderFragment);
            }
        });


        binding.closeActionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


    }
}