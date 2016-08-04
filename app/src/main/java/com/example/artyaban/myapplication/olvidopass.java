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

public class olvidopass extends AppCompatActivity {


    String TAG = "Response";
    Button bt;
    EditText text;
    String getCel;
    SoapPrimitive resultString;
    String correo="";

    View view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidopass);
    }


    public void enviarrr(View view )
    {

     view2=view;

        EditText correoo = (EditText) findViewById(R.id.editText3);
        correo= correoo.getText().toString();

        if(correo.equals("") )
        {

            AlertDialog alertDialog = new AlertDialog.Builder(olvidopass.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("INGRESA TU CORREO");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }else{

            AsyncCallWS task = new AsyncCallWS();
            task.execute();



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
            enviacorreo(correo);
            return null;
        }


        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

    if(resultString.toString().equals("true"))
    {
        Intent cambiar = new Intent(getApplicationContext(),inicio.class);
         startActivityForResult(cambiar, 0);
    }else{
        AlertDialog alertDialog = new AlertDialog.Builder(olvidopass.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("CORREO NO ENVIADO INTENTA DE NUEVO.");
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

    public void enviacorreo(String correo) {
        String SOAP_ACTION = "http://tempuri.org/Correo";
        String METHOD_NAME = "Correo";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://187.188.159.205:8090/webServiceNomina/Service.asmx";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty("correo",correo);


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

