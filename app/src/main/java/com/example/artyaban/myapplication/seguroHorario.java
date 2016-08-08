package com.example.artyaban.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class seguroHorario extends AppCompatActivity {
    String entradaa;
    String salidaa;
    String jornadaString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguro_horario);
        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");
        TextView entrada = (TextView) findViewById(R.id.HoraEntrada);
        TextView jornada = (TextView) findViewById(R.id.jornada);
        TextView salida = (TextView) findViewById(R.id.HoraSalida);
        entrada.setText(entradaa);
        salida.setText(salidaa);

            String tomarHorasEntrada="";
            String tomarHorasSalida="";
            String tomarMinutosEntrada="";
            String tomarMinutosSalida="";

            tomarHorasEntrada = entradaa.substring(0,2);
            tomarMinutosEntrada = entradaa.substring(3,5);
            tomarHorasSalida = salidaa.substring(0,2);
            tomarMinutosSalida = salidaa.substring(3,5);


            String TAG = "Response";
            Log.i(TAG, "HORAS TOAMDAS EN STRING");
            Log.i(TAG,tomarHorasEntrada);
            Log.i(TAG,tomarMinutosEntrada);
            Log.i(TAG,tomarHorasSalida);
            Log.i(TAG,tomarMinutosSalida);


            int HorasEntrada = Integer.parseInt(tomarHorasEntrada);
            int MinutosEntrada = Integer.parseInt(tomarMinutosEntrada);
            int tiempoMinutosEntrada;

            tiempoMinutosEntrada= (HorasEntrada*60)+MinutosEntrada;

            Log.i(TAG, "MINUTOS ENTRADA INT");
            Log.i(TAG, Integer.toString(tiempoMinutosEntrada));

            int HorasSalida = Integer.parseInt(tomarHorasSalida);
            int MinutosSalida = Integer.parseInt(tomarMinutosSalida);
            int tiempoMinutosSalida;

            tiempoMinutosSalida= (HorasSalida*60)+MinutosSalida;

            Log.i(TAG, "MINUTOS SALIDA INT");
            Log.i(TAG,Integer.toString(tiempoMinutosSalida));

            int v1= 720 - tiempoMinutosEntrada;
            int v2 = v1+ tiempoMinutosSalida;
            int v3=0;

            if(v2>720) {
            v3 = v2 - 720;
            }else{v3=v2;}

            int totaljornada = v3/60;
            jornadaString = Integer.toString(totaljornada);
            jornada.setText(jornadaString);

    }


    public void  si(View view)
    {

        Intent intent = new Intent(seguroHorario.this, scannerEntrada.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);

    }

    public void  no(View view)
    {
        Intent intent = new Intent(seguroHorario.this, registro.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);

    }

  //  Intent intent = new Intent(registro.this, scannerEntrada.class);
  //  intent.putExtra("entrada", entradaa);
    //intent.putExtra("salida", salidaa);
   // startActivity(intent);
}
