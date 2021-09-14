package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.databinding.FragmentPrestamoClientBinding;
import com.anthony.moneylender.databinding.FragmentRegistrarClientBinding;
import com.anthony.moneylender.implement.MySnackbar;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterClientModel;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterNewLender;
import com.anthony.moneylender.ui.PrincipalMenu.IcomunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class PrestamoClientFragment extends Fragment implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private FloatingActionButton return_,administra_,about_ ;
    private FragmentPrestamoClientBinding binding;
    private View root;
    private RegisterNewLender viewModel;
    private IcomunicaFragments interfacesFragment;
    private Activity activity;
    private DataBaseMoney db;
    private LiveData<List<Cliente>> clientLiveData;
    private ArrayAdapter adaptador;
    private ArrayList clientList;
    private MySnackbar mySnackbar;

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



        binding.searchClient.setOnQueryTextListener(this);
    }

    private void eventClick() {




        binding.btnAcceptLender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.idClientLenderAssigned.getText().toString().isEmpty()) {
                    if (!binding.termDues.getText().toString().isEmpty() &&
                        !binding.interest.getText().toString().isEmpty() &&
                        !binding.amountLender.getText().toString().isEmpty() &&
                        !binding.dues.getText().toString().isEmpty()){

                    int interestLender,countDuesLender;
                    double amountLender,duesLender;
                    String termDues;
                    termDues = binding.termDues.getText().toString();
                    interestLender = Integer.parseInt(binding.interest.getText().toString());
                    amountLender = Double.parseDouble(binding.amountLender.getText().toString().replace(".,","").trim());
                    countDuesLender = Integer.parseInt(binding.dues.getText().toString());
                    Double total = calculateTotal(amountLender, interestLender);
                    duesLender = total/countDuesLender;

                    //CREAR OBJETO PRESTAMO, LLAMA FUNCION  CALCULATETOTAL
                    Prestamos prestamos = new Prestamos(amountLender, interestLender,
                            total,
                            duesLender, countDuesLender, termDues);

                    //SETEAR PARAMETROS FALTANTES LLAMA FUNCION  GETDATECURRENT

                    prestamos.setStateAndLimit(getDateCurrent(), binding.dateLender.getText().toString(),
                            "Activo", Integer.parseInt(binding.idClientLenderAssigned.getText().toString()));

                    viewModel.insertNewLender(prestamos,db);
                    mySnackbar = new MySnackbar("Registro Completo",root);

                    }else{
                         mySnackbar = new MySnackbar("Rellene todos los campos",root);
                    }
                }else {
                   mySnackbar = new MySnackbar("Seleccione el cliente",root);
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


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        searchUserToLender();
        return false;
    }

    private void searchUserToLender() {

        if (binding.searchClient.getQuery().toString().length() > 0){
            clientLiveData = db.interfaceDao().getClient(binding.searchClient.getQuery().toString());

            clientLiveData.observe((LifecycleOwner) getContext(), new Observer<List<Cliente>>() {
                @Override
                public void onChanged(List<Cliente> clientes) {
                    if (clientes.size() > 0){
                        createListSearch(clientes);
                    }else{

                        return;
                    }

                }
            });
        }else{
            cleanInterface();

        }

    }

    private void cleanInterface() {

        clientLiveData = null;
        clientList = null;
        binding.arrayClient.setVisibility(View.GONE);


    }

    private void createListSearch(List<Cliente> clientes) {

        //LLENA LISTA CON DATOS DEL CLIENTE
        clientList = new ArrayList();

        for (int i=0;i<clientes.size();i++) {
            clientList.add(clientes.get(i).getNombre_cliente()+" "+clientes.get(i).getApellido_cliente());
        }


        adaptador =new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,clientList);
        binding.arrayClient.setVisibility(View.VISIBLE);
        binding.arrayClient.setAdapter(adaptador);


        binding.arrayClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setearData(clientes,parent.getItemAtPosition(position).toString());
            }
        });








    }

    private void setearData(List<Cliente> clientes, String itemAtPosition) {

        for(int i = 0;i < clientes.size(); i++){
            String condicion = clientes.get(i).getNombre_cliente()+" "+clientes.get(i).getApellido_cliente();

            if (condicion.equalsIgnoreCase(itemAtPosition)){


                binding.fullNameClient.setText(condicion);
                binding.fullPhoneClient.setText(clientes.get(i).getTelefono_cliente());
                binding.fullAdressClient.setText(clientes.get(i).getDireccion_cliente());
                binding.idClientLenderAssigned.setText(""+clientes.get(i).getId_cliente());

            }

        }
        binding.searchClient.setQuery("",false);
        binding.arrayClient.setVisibility(View.GONE);
        clientLiveData = null;
        clientList = null;

    }


}