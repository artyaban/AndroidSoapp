package com.example.artyaban.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class scannerEntrada extends AppCompatActivity {

    public String entradaa;
    public String salidaa;
    public String TAG = "Response";
    public SoapPrimitive resultString;
    public String numeroempleado;
    public String horass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_entrada);
        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");
        horass=getIntent().getExtras().getString("horas");
        Log.e(TAG, "recreada");
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();


    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {



            String scanContent = "";
            scanContent=scanningResult.getContents();

            numeroempleado = scanContent;

            AsyncCallWS task = new AsyncCallWS();
            task.execute();






        }else{


            Intent intent2 = new Intent(scannerEntrada.this,registroFalse.class);
            intent2.putExtra("entrada",entradaa);
            intent2.putExtra("salida",salidaa);
            intent2.putExtra("horas",horass);
            startActivity(intent2);

        }
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

            try{

            if(resultString.toString().equals("true"))
            {
                numeroempleado ="";
                Intent intent2 = new Intent(scannerEntrada.this,registroOk.class);
                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                intent2.putExtra("horas",horass);
                startActivity(intent2);



            }else{

                numeroempleado ="";
                Intent intent2 = new Intent(scannerEntrada.this,registroFalse.class);
                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                intent2.putExtra("horas",horass);
                startActivity(intent2);

            }

            }catch (Exception err)
            {

                Intent intent2 = new Intent(scannerEntrada.this,registroFalse.class);
                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                intent2.putExtra("horas",horass);
                startActivity(intent2);



            }
        }

    }

    public void registrar() {


        String SOAP_ACTION = "http://tempuri.org/registro";
        String METHOD_NAME = "registro";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://187.188.159.205:8090/webServiceNomina/Service.asmx";

        try {

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Log.e(TAG, "idLista: " +seguroHorario.idLista);
            int listaa =  Integer.parseInt(seguroHorario.idLista);
            Request.addProperty("idLista",listaa);
            Request.addProperty("entrada",entradaa);
            Log.e(TAG, "Entrada: " +entradaa);
            Request.addProperty("salida", salidaa);
            Log.e(TAG, "Salida : " +salidaa);
            Log.e(TAG, "Numero de empleado : " +numeroempleado);
            Request.addProperty("numEmpleado",numeroempleado);
            int envhoras = Integer.parseInt(horass);
            Request.addProperty("horas",envhoras);
            Log.e(TAG, "horas : " +horass);



            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            resultString = (SoapPrimitive) soapEnvelope.getResponse();


        } catch (Exception ex) {

            numeroempleado ="";
            Intent intent2 = new Intent(scannerEntrada.this,registroFalse.class);
            intent2.putExtra("entrada",entradaa);
            intent2.putExtra("salida",salidaa);
            intent2.putExtra("horas",horass);
            startActivity(intent2);



        }
    }


}
