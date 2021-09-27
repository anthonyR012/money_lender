package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;
import com.anthony.moneylender.implement.OrderDataImplement;

import java.util.List;

public class AdapterRecycleLender extends RecyclerView.Adapter<AdapterRecycleLender.ViewHolder> {
    private List<OrderDataImplement> prestamos;

    public AdapterRecycleLender(List<OrderDataImplement> prestamos) {
        this.prestamos = prestamos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lender,null,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.AsignarDatos(prestamos.get(position));
    }



    @Override
    public int getItemCount() {
        return prestamos.size();
    }


        public class ViewHolder extends RecyclerView.ViewHolder {
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
