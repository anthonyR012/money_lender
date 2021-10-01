package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;
import com.anthony.moneylender.databinding.FragmentAdministrarClientBinding;
import com.anthony.moneylender.models.PrincipalMenuModel.Client.HistorialClientModel;
import com.anthony.moneylender.models.PrincipalMenuModel.Lender.HistorialLenderModel;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender.AdapterRecycleLender;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class HistorialClientFragment extends Fragment implements androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private FloatingActionButton return_,about_ ;
    private View root;
    private FragmentAdministrarClientBinding binding;
    private HistorialClientModel model;
    private DataBaseMoney db;
    private AdapterRecycleClient adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //INSTANCIACION DE BINDING Y MODELOS CON LOGICA
        binding = FragmentAdministrarClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        model = new ViewModelProvider(this).get(HistorialClientModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //INSTANCIACION  BOTONES FLOTANTES
        return_ = root.findViewById(R.id.Fb_returnIcon);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        db = DataBaseMoney.getInstance(getContext());
        final NavController navController = Navigation.findNavController(view);
        adapterItems();
        eventsClick(navController);
    }

    private void adapterItems() {

        binding.recycleItemClients.setLayoutManager(new GridLayoutManager(getContext(),1));
        //ENVIAMOS BASE DE DATOS Y SOLICITAMOS LA LISTA DE CLIENTES PARA ADAPTARLA
        model.setDb(db);
        model.getPrestamos().observe(getViewLifecycleOwner(), new Observer<List<ClientePrestamos>>() {
            @Override
            public void onChanged(List<ClientePrestamos> clientes) {

                adapter = new AdapterRecycleClient(clientes);
                binding.recycleItemClients.setAdapter(adapter);

            }
        });

    }

    private void eventsClick(NavController navController) {
        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historialClientFragment3_to_inicioFragmentMenu);
            }
        });

        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historialClientFragment3_to_acercaFragment);
            }
        });
        //EVENTO DE TEXTO AL BUSCADOR
        binding.searchClient.setOnQueryTextListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //CADA QUE SE CAMBIE EL TEXTO, SE EJECUTA ESTA FUNCION QUE FILTRA EL CONTENIDO DEL RECYCLE
        adapter.filtrado(newText);
        return false;
    }
}