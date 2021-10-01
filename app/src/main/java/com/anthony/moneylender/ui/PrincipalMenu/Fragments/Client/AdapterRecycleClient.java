package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;
import com.anthony.moneylender.implement.OrderDataImplement;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender.AdapterRecycleLender;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdapterRecycleClient extends RecyclerView.Adapter<AdapterRecycleClient.ViewHolderClient> {
    List<ClientePrestamos> clientes;
    List<ClientePrestamos> clienteOriginal;

    public AdapterRecycleClient(List<ClientePrestamos> clientes) {
        //CONTRUCTOR QUE RECIBE LISTA Y LA GUARDA EN DOS LISTAS DISTINTAS
        this.clientes = clientes;
        clienteOriginal = new ArrayList<>();
        clienteOriginal.addAll(clientes);
    }

    @NonNull
    @Override
    public ViewHolderClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INFLAMOS VISTA DEL LAYOUT
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client,null,false);
        return new ViewHolderClient(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClient holder, int position) {
        //EN VISTA CREADA ASIGNAMOS DATOS
        holder.AsignarDatos(clientes.get(position));
    }

    public void filtrado(String txtSearch){
        verifiedString(txtSearch);

    }


    private void verifiedString(String txtSearch) {
        //MIDE LONGITUD DE CADENA Y REALIZA BUSQUEDA EN RECYCLE
        int longitud = txtSearch.length();
        if (longitud == 0){

            //SI ES CERO LIMPIA DATOS DE LISTA Y UTILIZA LA SEGUNDA LISTA RESERVADA
            clientes.clear();
            clientes.addAll(clienteOriginal);

        }else{
            //CUANDO SEA MAYOR SE FILTRA POR ESTE METODO EL CONTENIDO
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ClientePrestamos> collecion
                        = clientes.stream()
                        .filter(i -> i.cliente.getNombre_cliente().toLowerCase().contains(txtSearch.toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());
                clientes.clear();
                clientes.addAll(collecion);

            }else{
                //SI ES SDK MAYOR A LA VERSION N, EJECUTA ESTE METODO
                for (ClientePrestamos p:clienteOriginal ) {
                    if (p.cliente.getNombre_cliente().toLowerCase(Locale.ROOT).contains(txtSearch.toLowerCase(Locale.ROOT))){
                        clientes.add(p);
                    }
                }
            }
        }
        //NOFICAMOS CAMBIOS PARA EL RECYCLE
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }



    public class ViewHolderClient extends RecyclerView.ViewHolder {
        TextView idItem;
        TextView countItem;
        TextView nameItem;
        TextView phoneItem;

        public ViewHolderClient(@NonNull View itemView) {
            super(itemView);
            idItem = itemView.findViewById(R.id.IdClientDetail);
            countItem = itemView.findViewById(R.id.countLender);
            nameItem = itemView.findViewById(R.id.nameClientDetail);
            phoneItem  = itemView.findViewById(R.id.phoneClientDetail);

        }

        public void AsignarDatos(ClientePrestamos cliente) {

            int count = cliente.prestamos.size();
            idItem.setText(""+cliente.cliente.id_cliente_pk);
            countItem.setText("Total Prestamos "+count);
            nameItem.setText(cliente.cliente.getNombre_cliente()+" "+cliente.cliente.getApellido_cliente());
            phoneItem.setText(cliente.cliente.getTelefono_cliente());


        }
    }
}
