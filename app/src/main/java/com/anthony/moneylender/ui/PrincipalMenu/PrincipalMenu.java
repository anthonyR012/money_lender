package com.anthony.moneylender.ui.PrincipalMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.anthony.moneylender.R;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.InicioFragmentMenu;

public class PrincipalMenu extends AppCompatActivity {
    private  Fragment initialFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);
         initialFragment = new InicioFragmentMenu();
         getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
         ,initialFragment).commit();

    }
}