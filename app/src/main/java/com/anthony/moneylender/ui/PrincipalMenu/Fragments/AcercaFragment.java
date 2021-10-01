package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;
import com.anthony.moneylender.databinding.FragmentAcercaBinding;
import com.anthony.moneylender.implement.MySnackbarImplement;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.SerializableUserImplement;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class AcercaFragment extends Fragment {

    private View root;
    private FloatingActionButton return_,administra_ ;
    private FragmentAcercaBinding binding;
    private SerializableUserImplement user;
    private MySnackbarImplement mySnackbarImplement;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAcercaBinding.inflate(inflater, container, false);
        root = binding.getRoot();



        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        user = (SerializableUserImplement) bundle.getSerializable("INFORMATION");

        return_ = view.findViewById(R.id.Fb_returnIcon);
        administra_ = view.findViewById(R.id.Fb_userAdministra);

        final NavController navController = Navigation.findNavController(view);

        eventsClick(navController);

    }

    private void eventsClick(NavController navController) {
    return_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            navController.navigate(R.id.action_acercaFragment_to_inicioFragmentMenu);
        }
    });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_acercaFragment_to_historialClientFragment3);
            }
        });

    binding.saveAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //VERIFICA CASILLAS SELECCIONADAS
            if (binding.checkSaveReports.isChecked() || binding.checkSaveCredential.isChecked()){
                verifyBoxSelect();
                mySnackbarImplement = new MySnackbarImplement("Preferencias registradas",root);
            }else{
                mySnackbarImplement = new MySnackbarImplement("No ha selecciónado nada",root);

            }

        }
    });

    binding.restableChange.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //CLICK QUE DESEA RESTABLECER LA INFORMACION
            reinstatement();
            mySnackbarImplement = new MySnackbarImplement("Restablecimiento completo",root);
        }
    });

    //EVENTOS CLICK DE BOTONES PARA REDES SOCIALES
        binding.iconFacce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String faccebookURL = getActivity().getString(R.string.url_facce);

                Uri uri =Uri.parse(faccebookURL);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        binding.iconInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String instagramURL = getActivity().getString(R.string.url_insta);
                Uri uri =Uri.parse(instagramURL);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        binding.iconWpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String whatsappURL = getActivity().getString(R.string.url_wpp);
                Uri uri =Uri.parse(whatsappURL);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }

    private void reinstatement() {
        //LIMPIA ARCHIVO DE PREFERENCIA
        Context context = getActivity();
        SharedPreferences.Editor editor = context.getSharedPreferences("credentiales", Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    private void verifyBoxSelect() {

        //CREA ARCHIVO PREFERENCIA CON LAS CASILLAS SELECCIONADAS
        Context context = getActivity();
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences("credentiales",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();;
        if (binding.checkSaveCredential.isChecked()){

            String userSave = user.getEmail();
            byte[] passSave = user.getPass();
            SecurityPassImplement implement = new SecurityPassImplement();
            editor.putString("user",userSave);
            editor.putString("pass", implement.descifra(passSave));



        }
        if (binding.checkSaveReports.isChecked()){
            boolean reportSave = true;
            editor.putBoolean("report",reportSave);
        }
        editor.commit();
    }




}