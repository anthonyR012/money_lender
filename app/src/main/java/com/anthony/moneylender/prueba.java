package com.anthony.moneylender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class prueba extends AppCompatActivity {
    EditText id,nombre,apellidos,telefono,direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        nombre = findViewById(R.id.nombre);
        id =  findViewById(R.id.id);
        telefono =  findViewById(R.id.telefono);
        apellidos =  findViewById(R.id.apellidos);
        direccion =  findViewById(R.id.direccion);


    }


    public void Prueba(View view){
        registUser();
    }

    private void registUser() {
    }
}