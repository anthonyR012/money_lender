package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RegistrarClientFragment extends Fragment {

    private FloatingActionButton  optiones;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root =  inflater.inflate(R.layout.fragment_registrar_client, container, false);
//        optiones = root.findViewById(R.id.floatingButton);
//        optiones.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
//            }
//        });
    return root;
    }
}