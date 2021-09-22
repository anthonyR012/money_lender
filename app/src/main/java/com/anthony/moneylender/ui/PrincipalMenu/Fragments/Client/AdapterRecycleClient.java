package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Client;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.Entidades.Relacion.ClientePrestamos;

public class AdapterRecycleClient extends RecyclerView.Adapter<AdapterRecycleClient.ViewHolderClient> {


    @NonNull
    @Override
    public ViewHolderClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClient holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }



    public class ViewHolderClient extends RecyclerView.ViewHolder {

        public ViewHolderClient(@NonNull View itemView) {
            super(itemView);
        }
    }
}
