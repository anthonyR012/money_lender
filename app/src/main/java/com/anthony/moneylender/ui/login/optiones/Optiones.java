package com.anthony.moneylender.ui.login.optiones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.anthony.moneylender.R;
import com.anthony.moneylender.ui.login.LoginActivity;
import com.anthony.moneylender.ui.login.optiones.fragments.SingUp;
import com.anthony.moneylender.ui.login.optiones.fragments.ForgotPass;

public class Optiones extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentSingUp,fragmentForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optiones);

        fragmentForgotPass = new ForgotPass();
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
        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.containerFragment,fragmentForgotPass).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(Optiones.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.registrarUser:
                fragmentTransaction.replace(R.id.containerFragment,fragmentSingUp);
                fragmentTransaction.addToBackStack(null);
                break;
            case R.id.restablecerPass:
                fragmentTransaction.replace(R.id.containerFragment,fragmentForgotPass);
                fragmentTransaction.addToBackStack(null);
                break;
        }
        fragmentTransaction.commit();
        return super.onOptionsItemSelected(item);
    }



}