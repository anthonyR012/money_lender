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
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

import java.util.List;

public class AdapterRecycleLender extends RecyclerView.Adapter<AdapterRecycleLender.ViewHolder> {
    private List<Prestamos> prestamos;
    public View view;
    int i = 0;
    int sizePrestamos;
    private Integer clientePrestamo = null;
    private boolean condicion = false;

    public AdapterRecycleLender(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lender,null,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if(clientePrestamo != null) {
//
//           position = clientePrestamo;
//        }
//
//       sizePrestamos = prestamos.get(position).prestamos.size();
////        while (i < sizePrestamos) {
//
////        Log.i("position", "prestamo"+prestamos.get(0).prestamos.get(0).getCantidad_prestamo()+" cliete "+prestamos.get(0).cliente.getNombre_cliente());
////        Log.i("position", "prestamo"+prestamos.get(0).prestamos.get(1).getCantidad_prestamo()+" cliete "+prestamos.get(0).cliente.getNombre_cliente());
////        Log.i("position", "prestamo"+prestamos.get(1).prestamos.get(0).getCantidad_prestamo()+" cliete "+prestamos.get(1).cliente.getNombre_cliente());
//
////Log.i("carga","");
//
//            Log.i("position", "prestamo "+i+"cliete pone"+position);
//            holder.AsignarDatos(prestamos.get(position), i);
//        }else{
//            Log.i("position", "prestamo "+i+"cliete psec"+clientePrestamo);
//            holder.AsignarDatos(prestamos.get(clientePrestamo), i);
//        }

//        if(sizePrestamos>1 && i+1 < sizePrestamos) {
//            if (clientePrestamo==null){
//                clientePrestamo = position;
//            }
//
////            condicion = true;
//        }else{
//            clientePrestamo = null;
////            condicion = false;
//        }
//
//        if(i==sizePrestamos-1){
//            i=0;
//
////            holder.AsignarDatos(prestamos.get(position), i);
//        }else{
//
//            i++;
//        }

//        (i<sizePrestamos)? int o=0; :i=0;

//            holder.AsignarDatos(prestamos.get(position), i);
//            if (i <sizePrestamos){

//            }else{
//                i = 0;
//            }



//        }
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
            View itemView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                amountItem = itemView.findViewById(R.id.amountDetailLender);
                dateInitItem = itemView.findViewById(R.id.dateInitDetailLender);
                dataFinItem = itemView.findViewById(R.id.dateFinDetailLender);
                clientLender  = itemView.findViewById(R.id.clientLenderDetail);

            }



            public void AsignarDatos(Prestamos prestamos) {


                amountItem.setText("$ "+Math.round(prestamos.getTotal_prestamo()));
                dateInitItem.setText(prestamos.getFecha_prestamo_init());
                dataFinItem.setText(prestamos.getFecha_prestamo_fin());
                clientLender.setText("Plazo "+prestamos.getPlazo_prestamo());


            }
        }
}
