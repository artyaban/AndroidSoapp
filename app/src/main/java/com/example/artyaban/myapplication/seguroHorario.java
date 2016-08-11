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
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class seguroHorario extends AppCompatActivity {
    String entradaa;
    String salidaa;
    String jornadaString;
    public static String idLista;
    public static String Horas;
    String TAG = "Response";
    Button bt;

    String getCel;
    SoapPrimitive resultString;
    String usuarioo  ;
    String passwordd;
    View view2;
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
        intent.putExtra("horas",jornadaString);
        startActivity(intent);

    }

    public void  no(View view)
    {
        Intent intent = new Intent(seguroHorario.this, registro.class);
        intent.putExtra("entrada", entradaa);
        intent.putExtra("salida", salidaa);
        startActivity(intent);

    }







    private class AsyncCallWS extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            registrar();
            return null;
        }


        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

            if(resultString.toString()!="false")
            {
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
                idLista =resultString.toString();
                Intent intent2 = new Intent(seguroHorario.this,registroOk.class);
                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                startActivity(intent2);

            }else{

                Intent intent2 = new Intent(seguroHorario.this,registroFalse.class);

                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                startActivity(intent2);

            }
        }

    }

    public void registrar() {
        String SOAP_ACTION = "http://tempuri.org/crearLista";
        String METHOD_NAME = "crearLista";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://187.188.159.205:8090/webServiceNomina/Service.asmx";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty("lider",inicio.lider);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();


        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }







}
