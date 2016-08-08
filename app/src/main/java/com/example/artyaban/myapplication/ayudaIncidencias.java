package com.example.artyaban.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ayudaIncidencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_incidencias);
    }

    public void cerrar(View view)
    {
        Intent intent = new Intent(ayudaIncidencias.this, lista.class);
        startActivity(intent);
    }

}
