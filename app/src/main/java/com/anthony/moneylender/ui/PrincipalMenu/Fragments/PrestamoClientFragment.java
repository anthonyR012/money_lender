package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.databinding.FragmentPrestamoClientBinding;
import com.anthony.moneylender.implement.MySnackbarImplement;
import com.anthony.moneylender.models.PrincipalMenuModel.RegisterNewLender;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PrestamoClientFragment extends Fragment implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private FloatingActionButton return_,administra_,about_ ;
    private FragmentPrestamoClientBinding binding;
    private View root;
    private RegisterNewLender viewModel;

    private Activity activity;
    private DataBaseMoney db;
    private LiveData<List<Cliente>> clientLiveData;
    private ArrayAdapter adaptador;
    private ArrayList clientList;
    private MySnackbarImplement mySnackbarImplement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPrestamoClientBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RegisterNewLender.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = DataBaseMoney.getInstance(getContext());
        return_ = view.findViewById(R.id.Fb_returnIcon);
        administra_ = view.findViewById(R.id.Fb_userAdministra);
        about_ = view.findViewById(R.id.Fb_aboutApplication);

        final NavController navController = Navigation.findNavController(view);

        eventsClick(navController);

        changeDataSearch();
    }

    private void changeDataSearch() {



        binding.searchClient.setOnQueryTextListener(this);
    }

    private void eventsClick(NavController navController) {

        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_prestamoClientFragment_to_inicioFragmentMenu);
            }
        });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_prestamoClientFragment_to_historialClientFragment3);
            }
        });
        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_prestamoClientFragment_to_historialClientFragment3);
            }
        });
        binding.btnAcceptLender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //VERIFICA CAMPOS LLENOS
                if (!binding.idClientLenderAssigned.getText().toString().isEmpty()) {
                    if (!binding.termDues.getText().toString().isEmpty() &&
                        !binding.interest.getText().toString().isEmpty() &&
                        !binding.amountLender.getText().toString().isEmpty() &&
                        !binding.dues.getText().toString().isEmpty()){
                    //DECLARA VARIABLES LOCALES
                    int interestLender,countDuesLender;
                    double amountLender,duesLender;
                    String termDues;
                    //INICIALIZA

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
                    mySnackbarImplement = new MySnackbarImplement("Registro Completo",root);

                    }else{
                         mySnackbarImplement = new MySnackbarImplement("Rellene todos los campos",root);
                    }
                }else {
                   mySnackbarImplement = new MySnackbarImplement("Seleccione el cliente",root);
                }

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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //CADA VEZ QUE BUSQUE EJECUTA FUNCION
        searchUserToLender();
        return false;
    }

    private void searchUserToLender() {
    //VERIFICA STRING Y EJECUTA CONSULTA
        if (binding.searchClient.getQuery().toString().length() > 0){
            clientLiveData = db.interfaceDao().getClient(binding.searchClient.getQuery().toString());

            clientLiveData.observe((LifecycleOwner) getContext(), new Observer<List<Cliente>>() {
                @Override
                public void onChanged(List<Cliente> clientes) {
                    if (clientes.size() > 0){
                        //BUSCA CLIENTE
                        createListSearch(clientes);
                    }else{

                        return;
                    }

                }
            });
        }else{
            //LIMPIA DATOS
            cleanInterface();

        }

    }

    private void cleanInterface() {

        clientLiveData = null;
        clientList = null;
        binding.arrayClient.setVisibility(View.GONE);
        if (binding.searchClient.getQuery().length() > 0) {
            binding.searchClient.setQuery("", false);
        }
         binding.searchClient.clearFocus();


    }

    private void createListSearch(List<Cliente> clientes) {

        //LLENA LISTA CON DATOS DEL CLIENTE
        clientList = new ArrayList();

        for (int i=0;i<clientes.size();i++) {
            clientList.add(clientes.get(i).getNombre_cliente()+" "+clientes.get(i).getApellido_cliente());
        }

        //ADAPTA POR MEDIO DEL LAYOUT
        adaptador =new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,clientList);
        binding.arrayClient.setVisibility(View.VISIBLE);
        binding.arrayClient.setAdapter(adaptador);

        //SI SE DA CLICK EN UN RESULTADO EJECUTA FUNCION
        binding.arrayClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setearData(clientes,parent.getItemAtPosition(position).toString());
            }
        });


    }

    private void setearData(List<Cliente> clientes, String itemAtPosition) {
        //FUNCION QUE BUSCA Y SETEA RESULTADO EN LAS CASILLAS
        for(int i = 0;i < clientes.size(); i++){
            String condicion = clientes.get(i).getNombre_cliente()+" "+clientes.get(i).getApellido_cliente();

            if (condicion.equalsIgnoreCase(itemAtPosition)){


                binding.fullNameClient.setText(condicion);
                binding.fullPhoneClient.setText(clientes.get(i).getTelefono_cliente());
                binding.fullAdressClient.setText(clientes.get(i).getDireccion_cliente());
                binding.idClientLenderAssigned.setText(""+clientes.get(i).getId_cliente());

            }

        }
        //LIMPIA NUEVAMENTE
        cleanInterface();

    }


}