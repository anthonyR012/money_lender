package com.anthony.moneylender.ui.login.optiones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.anthony.moneylender.R;
import com.anthony.moneylender.ui.login.optiones.fragments.SingUp;
import com.anthony.moneylender.ui.login.optiones.fragments.forgotPass;

public class Optiones extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentSingUp,fragmentForgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optiones);

        fragmentForgotPass = new forgotPass();
        fragmentSingUp = new SingUp();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle object = getIntent().getExtras();
        if(object!=null){
            String estado = (String) object.getSerializable("estado");
            if(estado.equals("registrar")){
                getSupportFragmentManager().beginTransaction().add(R.id.containerFragment,fragmentSingUp).commit();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.containerFragment,fragmentForgotPass).commit();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onclick(View view) {
        fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {

            case R.id.singUp:
            fragmentTransaction.replace(R.id.containerFragment,fragmentSingUp);
                fragmentTransaction.addToBackStack(null);
            break;
            case R.id.forgotPass:

            fragmentTransaction.replace(R.id.containerFragment,fragmentForgotPass);
                fragmentTransaction.addToBackStack(null);
            break;

        }
        fragmentTransaction.commit();
    }
}