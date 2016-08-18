package com.example.artyaban.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class registroFalse extends AppCompatActivity {
    public String entradaa;
    public String salidaa;
    public String horass;
    public String idlista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_false);
        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");
        horass=getIntent().getExtras().getString("horas");
        idlista=getIntent().getExtras().getString("idLista");

    }
    public void regresa(View view)
    {

        Intent intent=new Intent();

        //llamamos a la actividad

        Intent intent2 = new Intent(registroFalse.this,scannerEntrada.class);

        intent2.putExtra("entrada",entradaa);
        intent2.putExtra("salida",salidaa);
        intent2.putExtra("horas",horass);
        intent2.putExtra("idLista",idlista);
        startActivity(intent2);


    }

}
