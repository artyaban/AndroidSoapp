package com.example.artyaban.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class lista extends AppCompatActivity {



    String TAG = "Response";
    Button bt;

    String getCel;
    SoapPrimitive resultString;
    String usuarioo  ;
    String passwordd;
    View view2;

    Activity actividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);


        //cambia por foreach
        for(int i = 0; i < 15; i++)
        {
            ArrayList<String> elementos = new ArrayList<String>();

            elementos.add("inspector "+ i );
            elementos.add("AGREGADA" );
            elementos.add("correcto" );

            tabla.agregarFilaTabla(elementos);
        }

    }
    public void ayuda(View view)
    {
        Intent intent = new Intent(lista.this, ayudaIncidencias.class);
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
            Login(usuarioo,passwordd);
            return null;
        }


        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

            if(resultString.toString().equals("true"))
            {

                Intent cambiar = new Intent(getApplicationContext(), registro.class);
                startActivityForResult(cambiar, 0);

            }else{
                Log.i(TAG, "estoy en else");
                AlertDialog alertDialog = new AlertDialog.Builder(lista.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("REVISA TU USUARIO Y CONTRASEÑA");
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

    public void Login(String user,String pass) {
        String SOAP_ACTION = "http://tempuri.org/Login";
        String METHOD_NAME = "Login";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://187.188.159.205:8090/webServiceNomina/Service.asmx";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty("lider", inicio.lider);


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



