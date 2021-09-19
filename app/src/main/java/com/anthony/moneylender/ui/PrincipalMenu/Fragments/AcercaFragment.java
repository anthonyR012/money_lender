package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.databinding.FragmentAcercaBinding;
import com.anthony.moneylender.databinding.FragmentPerfilAmdBinding;
import com.anthony.moneylender.implement.MySnackbar;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;


public class AcercaFragment extends Fragment {
    private View root;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    private FloatingActionButton return_,administra_,about_ ;
    private FragmentAcercaBinding binding;
    private SerializableUserImplement user;
    private MySnackbar mySnackbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAcercaBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        user = (SerializableUserImplement) getArguments().getSerializable("INFORMATION");
        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);

        eventsClick();
        return root;

    }

    private void eventsClick() {

    binding.saveAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (binding.checkSaveReports.isChecked() || binding.checkSaveCredential.isChecked()){
                verifyBoxSelect();
                mySnackbar = new MySnackbar("Preferencias registradas",root);
            }else{
                mySnackbar = new MySnackbar("No ha selecciónado nada",root);

            }

        }
    });

    binding.restableChange.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reinstatement();
            mySnackbar = new MySnackbar("Restablecimiento completo",root);
        }
    });
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

    private void reinstatement() {
        Context context = getActivity();
        SharedPreferences.Editor editor = context.getSharedPreferences("credentiales", Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    private void verifyBoxSelect() {
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



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            interfacesFragment = (IcomunicaFragments) activity;
        }
    }
}