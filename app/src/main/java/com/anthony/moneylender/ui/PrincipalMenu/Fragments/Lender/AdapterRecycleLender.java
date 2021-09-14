package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

import java.util.List;

public class AdapterRecycleLender extends RecyclerView.Adapter<AdapterRecycleLender.ViewHolder> {
    List<Prestamos> prestamos;

    public AdapterRecycleLender(List<Prestamos> products) {
        this.prestamos = products;
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
        holder.asignarDatos(prestamos.get(position));
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

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                amountItem = itemView.findViewById(R.id.amountDetailLender);
                dateInitItem = itemView.findViewById(R.id.dateInitDetailLender);
                dataFinItem = itemView.findViewById(R.id.dateFinDetailLender);
                clientLender  = itemView.findViewById(R.id.clientLenderDetail);
            }
            public void asignarDatos(Prestamos prestamos) {

                Log.i("dato ", prestamos.getPlazo_prestamo());
                amountItem.setText(""+prestamos.getCantidad_prestamo());
                dateInitItem.setText(prestamos.getFecha_prestamo_init());
                dataFinItem.setText(prestamos.getFecha_prestamo_fin());
                clientLender.setText(""+prestamos.getClientes_prestamo_fk());
            }

        }
}
