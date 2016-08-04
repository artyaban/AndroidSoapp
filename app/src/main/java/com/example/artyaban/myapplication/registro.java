package com.example.artyaban.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class registro extends AppCompatActivity {




    String TAG = "Response";
    Button bt;

    String getCel;
    SoapPrimitive resultString;
    String entradaa  ;
    String salidaa;
    View view2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void registro(View view)
    {


        EditText entrada = (EditText) findViewById(R.id.timePickerEntrada);
        EditText salida = (EditText) findViewById(R.id.timePickerSalida);
        entradaa = entrada.getText().toString();
        salidaa = salida.getText().toString();



        Intent intent = new Intent(registro.this, scannerEntrada.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);


    }






}
