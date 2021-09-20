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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.anthony.moneylender.ui.PrincipalMenu.PrincipalMenu;
import com.anthony.moneylender.ui.login.LoginActivity;

public class InicioFragmentMenu extends Fragment {

    private SerializableUserImplement user;
    private Bitmap photo;
    private FragmentInicioMenuBinding binding;
    private ImageView imagenAdapter;
    private CardView fbtAdministrar,fbtRegistrar,fbtPefil,
            fbtPrestamo,fbtAcerca,fbtHistorial;
    private View root;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         user = (SerializableUserImplement) getArguments().getSerializable("INFORMATION");
        binding = FragmentInicioMenuBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        if(user.getPhotoUser()!= null){
            photo = decode(user.getPhotoUser());

            imagenAdapter= root.findViewById(R.id.photoUserSingin);
            imagenAdapter.setImageBitmap(photo);
        }


        fbtAdministrar = root.findViewById(R.id.cardAdministrar);
        fbtRegistrar = root.findViewById(R.id.cardRegistrar);
        fbtPefil = root.findViewById(R.id.cardPerfil);
        fbtPrestamo = root.findViewById(R.id.cardPrestamo);
        fbtAcerca = root.findViewById(R.id.cardAcerca);
        fbtHistorial = root.findViewById(R.id.cardHistorial);

        eventsClick();

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            interfacesFragment = (IcomunicaFragments) activity;
        }
    }




    private void eventsClick() {

        binding.closeActionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

       fbtAdministrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
interfacesFragment.AdministrarClient();
           }
       });
       fbtAcerca.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
interfacesFragment.Acerca();
           }
       });
       fbtHistorial.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
interfacesFragment.Historial();
           }
       });
       fbtPrestamo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                interfacesFragment.PrestamoClient();
           }
       });
       fbtRegistrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               interfacesFragment.RegistrarClient();
           }
       });
       fbtPefil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                interfacesFragment.PerfilAdm();
           }
       });
    }
}