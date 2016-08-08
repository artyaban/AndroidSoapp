package com.example.artyaban.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class registroOk extends AppCompatActivity {
    String entradaa;
    String salidaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ok);

        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");

    }
    public void otro(View view)
    {

        Intent intent = new Intent(registroOk.this, scannerEntrada.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);
    }

    public void fin(View view)
    {
        Intent intent = new Intent(registroOk.this, lista.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);
    }
}
