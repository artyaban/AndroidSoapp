package com.example.artyaban.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class registroOk extends AppCompatActivity {
    public String entradaa;
    public String salidaa;
    public String horass;
    public String idlista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ok);
        horass=getIntent().getExtras().getString("horas");
        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");
        idlista=getIntent().getExtras().getString("idLista");

    }
    public void otro(View view)
    {

        Intent intent = new Intent(registroOk.this, scannerEntrada.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        intent.putExtra("horas", horass);
        intent.putExtra("idLista", idlista);
        startActivity(intent);
    }

    public void fin(View view)
    {
        Intent intent = new Intent(registroOk.this, lista.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        intent.putExtra("idLista", idlista);
        startActivity(intent);
    }
}
