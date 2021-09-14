package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.databinding.FragmentForgotPassBinding;
import com.anthony.moneylender.databinding.FragmentHistorialBinding;
import com.anthony.moneylender.databinding.FragmentPerfilAmdBinding;
import com.anthony.moneylender.models.PrincipalMenuModel.Lender.HistorialLenderModel;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class HistorialLenderFragment extends Fragment {

    private FloatingActionButton return_,administra_,about_ ;
    private View root;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    private FragmentHistorialBinding binding;
    private HistorialLenderModel model;
    private DataBaseMoney db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          // Inflate the layout for this fragment
        binding = FragmentHistorialBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        model = new ViewModelProvider(this).get(HistorialLenderModel.class);
        db = DataBaseMoney.getInstance(getContext());
        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        eventClick();
        adapterItems();
        return root;
    }

    private void adapterItems() {
        
        
        binding.recycleItemLenders.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        
        model.setDb(db);
        model.getPrestamos().observe(getViewLifecycleOwner(), new Observer<List<Prestamos>>() {
            @Override
            public void onChanged(List<Prestamos> prestamos) {
                AdapterRecycleLender adapter = new AdapterRecycleLender(prestamos);
                Log.i("Count", String.valueOf(adapter.getItemCount()));
                binding.recycleItemLenders.setAdapter(adapter);
            }
        });
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