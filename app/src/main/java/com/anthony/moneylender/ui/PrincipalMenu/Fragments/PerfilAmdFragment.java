package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;


public class PerfilAmdFragment extends Fragment {

    private FloatingActionButton return_,administra_,about_ ;
    private View root;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_perfil_amd, container, false);
        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        eventClick();
        return root;
    }


    private void eventClick() {

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