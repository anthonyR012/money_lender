package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;
import com.anthony.moneylender.implement.OrderDataImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdapterRecycleLender extends RecyclerView.Adapter<AdapterRecycleLender.ViewHolder> {
    private List<OrderDataImplement> prestamos;
    private List<OrderDataImplement> prestamosOriginal;


    public AdapterRecycleLender(List<OrderDataImplement> prestamos) {
        //CONTRUCTOR QUE RECIBE LISTA Y LA GUARDA EN DOS LISTAS DISTINTAS
        this.prestamos = prestamos;
        prestamosOriginal = new ArrayList<>();
        prestamosOriginal.addAll(prestamos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//INFLAMOS VISTA DEL LAYOUT
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lender,null,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//EN VISTA CREADA ASIGNAMOS DATOS
        holder.AsignarDatos(prestamos.get(position));
    }
    public void filtrado(String txtSearch){
        verifiedString(txtSearch);

    }


    private void verifiedString(String txtSearch) {
        //MIDE LONGITUD DE CADENA Y REALIZA BUSQUEDA EN RECYCLE

        int longitud = txtSearch.length();
        if (longitud == 0){
            //SI ES CERO LIMPIA DATOS DE LISTA Y UTILIZA LA SEGUNDA LISTA RESERVADA
            prestamos.clear();
            prestamos.addAll(prestamosOriginal);

        }else{
            //CUANDO SEA MAYOR SE FILTRA POR ESTE METODO EL CONTENIDO
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<OrderDataImplement> collecion
                        = prestamos.stream()
                        .filter(i -> i.getClientePrestamo().toLowerCase().contains(txtSearch.toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());
                prestamos.clear();
                prestamos.addAll(collecion);

            }else{
                //SI ES SDK MAYOR A LA VERSION N, EJECUTA ESTE METODO
                for (OrderDataImplement p:prestamosOriginal ) {
                    if (p.getClientePrestamo().toLowerCase(Locale.ROOT).contains(txtSearch.toLowerCase(Locale.ROOT))){
                        prestamos.add(p);
                    }
                }
            }
        }
        //NOFICAMOS CAMBIOS PARA EL RECYCLE
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return prestamos.size();
    }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView amountItem;
            TextView dateInitItem;
            TextView dataFinItem;
            TextView clientLender;
            TextView estadoItem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                amountItem = itemView.findViewById(R.id.amountDetailLender);
                dateInitItem = itemView.findViewById(R.id.dateInitDetailLender);
                dataFinItem = itemView.findViewById(R.id.dateFinDetailLender);
                clientLender  = itemView.findViewById(R.id.clientLenderDetail);
                estadoItem = itemView.findViewById(R.id.stateLenderItem);
            }



            public void AsignarDatos(OrderDataImplement prestamos) {


                amountItem.setText("$ "+prestamos.getCantidadPrestamo());
                dateInitItem.setText(prestamos.getFechaInit());
                dataFinItem.setText(prestamos.getFechaFin());
                clientLender.setText(prestamos.getClientePrestamo());
                estadoItem.setText(prestamos.getEstado());

            }
        }
}
