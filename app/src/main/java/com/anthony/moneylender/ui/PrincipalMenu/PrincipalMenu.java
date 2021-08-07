package com.anthony.moneylender.ui.PrincipalMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.InicioFragmentMenu;

public class PrincipalMenu extends AppCompatActivity {
    private  Fragment initialFragment;
    private SerializableUserImplement administrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);

        Bundle bundle = getIntent().getExtras();
        administrador = (SerializableUserImplement) bundle.getSerializable("INFORMATION");

        Bundle args = new Bundle();
        args.putSerializable("INFORMATION",administrador);

         initialFragment = new InicioFragmentMenu();
         initialFragment.setArguments(args);
         getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
         ,initialFragment).commit();

    }
}