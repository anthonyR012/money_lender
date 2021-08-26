package com.anthony.moneylender.ui.PrincipalMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.AcercaFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.AdministrarClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.lender.HistorialFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.InicioFragmentMenu;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PerfilAmdFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.PrestamoClientFragment;
import com.anthony.moneylender.ui.PrincipalMenu.Fragments.RegistrarClientFragment;

public class PrincipalMenu extends AppCompatActivity implements IcomunicaFragments {
    private  Fragment initialFragment,administrarFragment,
            registrarFragment,acercaFragment,perfilFragment
            ,historialFragment,prestamoFragment;
    private SerializableUserImplement administrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);

        Bundle bundle = getIntent().getExtras();
        administrador = (SerializableUserImplement) bundle.getSerializable("INFORMATION");

        Bundle args = new Bundle();
        args.putSerializable("INFORMATION",administrador);
        administrarFragment = new AdministrarClientFragment();
         initialFragment = new InicioFragmentMenu();
         registrarFragment = new RegistrarClientFragment();
         acercaFragment = new AcercaFragment();
        perfilFragment = new PerfilAmdFragment();
         historialFragment = new HistorialFragment();
         prestamoFragment = new PrestamoClientFragment();

         initialFragment.setArguments(args);

         getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
         ,initialFragment).commit();

    }


    @Override
    public void RegistrarClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,registrarFragment).commit();
    }

    @Override
    public void AdministrarClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,administrarFragment).commit();
    }

    @Override
    public void PerfilAdm() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,perfilFragment).commit();
    }

    @Override
    public void PrestamoClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,prestamoFragment).commit();
    }

    @Override
    public void Acerca() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,acercaFragment).commit();
    }

    @Override
    public void Historial() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentMenu
                ,historialFragment).commit();
    }
}