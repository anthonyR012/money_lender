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
import androidx.recyclerview.widget.LinearLayoutManager;

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


public class HistorialClientFragment extends Fragment {
    private FloatingActionButton return_,administra_,about_ ;
    private View root;
    private FragmentAdministrarClientBinding binding;
    private HistorialClientModel model;
    private DataBaseMoney db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdministrarClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        model = new ViewModelProvider(this).get(HistorialClientModel.class);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        return_ = root.findViewById(R.id.Fb_returnIcon);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        db = DataBaseMoney.getInstance(getContext());
        final NavController navController = Navigation.findNavController(view);
        adapterItems();
        eventsClick(navController);
    }

    private void adapterItems() {
        binding.recycleItemClients.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        model.setDb(db);

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

    }


}