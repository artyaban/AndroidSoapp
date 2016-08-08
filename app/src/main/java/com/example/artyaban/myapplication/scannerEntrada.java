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
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class scannerEntrada extends AppCompatActivity {

    String entradaa;
    String salidaa;


    String TAG = "Response";
    Button bt;

    String getCel;
    SoapPrimitive resultString;
    String usuarioo  ;
    String passwordd;
    View view2;
    String numeroempleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_entrada);
        entradaa=getIntent().getExtras().getString("entrada");
        salidaa=getIntent().getExtras().getString("salida");

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();


    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {


            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();


            //Desplegamos en pantalla el nombre del formato del código de barra scaneado

            String scanFormat = scanningResult.getFormatName();

            numeroempleado = scanContent;


            AsyncCallWS task = new AsyncCallWS();
            task.execute();






        }else{

            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
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

            if(resultString.toString().equals("true"))
            {


                Intent intent2 = new Intent(scannerEntrada.this,registroOk.class);

                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
                startActivity(intent2);

            }else{

                Intent intent2 = new Intent(scannerEntrada.this,registroFalse.class);

                intent2.putExtra("entrada",entradaa);
                intent2.putExtra("salida",salidaa);
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

            Request.addProperty("entrada",entradaa);
            Request.addProperty("salida", salidaa);
            Request.addProperty("numEmpleado",numeroempleado);


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
