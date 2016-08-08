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

    public void registrar(View view)
    {

        boolean bandera=true;
        EditText entrada = (EditText) findViewById(R.id.editText4);
        EditText salida = (EditText) findViewById(R.id.editText5);
        try {
            entradaa = entrada.getText().toString();
            salidaa = salida.getText().toString();
        }catch(Exception e)
        {
            bandera=false;
            entradaa="";
            salidaa="";
        }

        if(bandera=true) {


            if (entradaa.length() == 5) {
                bandera = true;
            } else {
                bandera = false;
            }
            if (bandera == true) {

                if (salidaa.length() == 5) {
                    bandera = true;
                } else {
                    bandera = false;
                }
                if (bandera == true) {

                    if (entradaa.substring(2, 3).equals(":")) {
                        bandera = true;
                    } else {
                        bandera = false;
                    }
                    if (bandera = true) {
                        if (salidaa.substring(2, 3).equals(":")) {
                            bandera = true;
                        } else {
                            bandera = false;
                        }
                    }
                }
            }
        }






    if(bandera==true) {

        Intent intent = new Intent(registro.this, seguroHorario.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);

    }else{

        entrada.setText("");
        salida.setText("");
        AlertDialog alertDialog = new AlertDialog.Builder(registro.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("POR FAVOR INGRESA EL FORMATO CORRECTAMENTE DEBE DE SER 00:00");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    }






}
