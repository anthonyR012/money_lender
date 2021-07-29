package com.anthony.moneylender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;

public class prueba extends AppCompatActivity {
    private EditText id,nombre,apellidos,telefono,direccion;
    private DataBaseMoney db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

         db = DataBaseMoney.getInstance(this);
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
        Cliente personas = new
                Cliente(id.getText().toString(),nombre.getText().toString(),apellidos.getText().toString(),
                direccion.getText().toString(),telefono.getText().toString(),"A01");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                db.interfaceDao().insertAll(personas);
            }
        });

    }
}