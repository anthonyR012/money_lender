package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.databinding.FragmentPrestamoClientBinding;
import com.anthony.moneylender.databinding.FragmentRegistrarClientBinding;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterClientModel;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterNewLender;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PrestamoClientFragment extends Fragment {

    private FloatingActionButton return_,administra_,about_ ;
    private FragmentPrestamoClientBinding binding;
    private View root;
    private RegisterNewLender viewModel;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    private DataBaseMoney db;
    private LiveData<List<Cliente>> cliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPrestamoClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RegisterNewLender.class);
        db = DataBaseMoney.getInstance(getContext());
        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        eventClick();
        changeDataSearch();


        return root;
    }

    private void changeDataSearch() {
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore

            }

            @Override
            public void afterTextChanged(Editable s) {

//                viewModel.ClientDataChanged(db,binding.searchClient.getText().toString());
            cliente = db.interfaceDao().getClient(binding.searchClient.getText().toString());
            if (cliente != null){
                cliente.observe((LifecycleOwner) getContext(), new Observer<List<Cliente>>() {
                    @Override
                    public void onChanged(List<Cliente> clientes) {
                        Toast.makeText(getContext(), ""+clientes.get(0).getNombre_cliente(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Log.i("Error","Sin data");
            }
            }
        };
        binding.searchClient.addTextChangedListener(afterTextChangedListener);

//        viewModel.getClientResult().observe((LifecycleOwner) getContext(), new Observer<List<Cliente>>() {
//            @Override
//            public void onChanged(List<Cliente> clientes) {
//
//            }
//        });



    }

    private void eventClick() {


        binding.searchClient.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= ( binding.searchClient.getRight() -  binding.searchClient.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        searchUserToLender();
                        return true;
                    }
                }
                return false;
            }
        });

        binding.btnAcceptLender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int interestLender,countDuesLender;
                double amountLender,duesLender;
                String termDues;
                termDues = binding.termDues.getText().toString();
                interestLender = Integer.parseInt(binding.interest.getText().toString());
                countDuesLender = Integer.parseInt(binding.countDues.getText().toString());
                amountLender = Double.parseDouble(binding.amountLender.getText().toString());
                duesLender = Double.parseDouble(binding.dues.getText().toString());

                if (!binding.idClientLenderAssigned.getText().toString().isEmpty()) {


                    //CREAR OBJETO PRESTAMO, LLAMA FUNCION  CALCULATETOTAL
                    Prestamos prestamos = new Prestamos(amountLender, interestLender,
                            calculateTotal(amountLender, interestLender),
                            duesLender, countDuesLender, termDues);

                    //SETEAR PARAMETROS FALTANTES LLAMA FUNCION  GETDATECURRENT

                    prestamos.setStateAndLimit(getDateCurrent(), binding.dateLender.getText().toString(),
                            "Activo", Integer.parseInt(binding.idClientLenderAssigned.getText().toString()));

                    viewModel.insertNewLender(prestamos,db);
                }

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

    private void searchUserToLender() {


    }

    private String getDateCurrent() {
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String salida = df.format(fecha);
        return salida;
    }

    //OPERACION TOTAL SEGUN INTERES DE PRESTAMO
    private Double calculateTotal(double amountLender, int interestLender) {

        return ((amountLender * interestLender)/100) + amountLender;
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