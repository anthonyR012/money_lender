package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.PrestamosClientes;
import com.anthony.moneylender.databinding.FragmentHistorialBinding;
import com.anthony.moneylender.implement.OrderDataImplement;
import com.anthony.moneylender.models.PrincipalMenuModel.Lender.HistorialLenderModel;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


public class HistorialLenderFragment extends Fragment implements androidx.appcompat.widget.SearchView.OnQueryTextListener{

    private FloatingActionButton return_,administra_,about_ ;
    private View root;
    private FragmentHistorialBinding binding;
    private HistorialLenderModel model;
    private DataBaseMoney db;
    private AdapterRecycleLender adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          // Inflate the layout for this fragment
        binding = FragmentHistorialBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        model = new ViewModelProvider(this).get(HistorialLenderModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = DataBaseMoney.getInstance(getContext());
        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);

        final NavController navController = Navigation.findNavController(view);

        eventsClick(navController);
        adapterItems();
    }

    private void adapterItems() {
        
        
        binding.recycleItemLenders.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        model.setDb(db);
        model.getPrestamos().observe(getViewLifecycleOwner(), new Observer<List<PrestamosClientes>>() {
            @Override
            public void onChanged(List<PrestamosClientes> prestamosClientes) {
                //CREAMOS LISTA CON LOS DATOS NECESARIOS DE LA VISTA HOLDER
                List<OrderDataImplement> listClientPrestamo = new ArrayList<>();
                //ORGANIZAMOS ESAS DATOS POR MEDIO DE UN OBJETO DE TIPO ORDERDATAIMPLEMENT
                for (int i = 0;i< prestamosClientes.size();i++){
                    for (int e =0;e<prestamosClientes.get(i).clientes.size();e++){
                    OrderDataImplement implement = new OrderDataImplement
                            (Integer.parseInt((""+Math.round(prestamosClientes.get(i).prestamos.getTotal_prestamo()))),
                                        prestamosClientes.get(i).prestamos.getFecha_prestamo_init(),
                                        prestamosClientes.get(i).prestamos.getFecha_prestamo_fin(),
                                        prestamosClientes.get(i).clientes.get(e).getNombre_cliente(),
                                        prestamosClientes.get(i).prestamos.getEstado_prestamo());

                        listClientPrestamo.add(implement);

                    }


                }

//                PASAMOS LISTA POR PARAMETRO AL ADAPTADOR
                        adapter = new AdapterRecycleLender(listClientPrestamo);
                        binding.recycleItemLenders.setAdapter(adapter);


            }
        });

    }

    private void eventsClick(NavController navController) {
        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historialLenderFragment_to_inicioFragmentMenu);
            }
        });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historialLenderFragment_to_historialClientFragment3);
            }
        });
        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historialLenderFragment_to_acercaFragment);
            }
        });

        binding.searchLender.setOnQueryTextListener(this);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText!=null && adapter!=null){
            adapter.filtrado(newText);
        }

        return false;
    }
}