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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class lista extends AppCompatActivity {



    String TAG = "Response";
    Button bt;
    ArrayList<String> elementos = new ArrayList<String>();
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


        AsyncCallWS task = new AsyncCallWS();
        task.execute();



        String [][] informacion={{"Pepito","AGREGADA","sdfsdf"},{"adasda","NOAGREGADA","asdasda"}};

        for ( String inspector[] : informacion ) {


            for(String inspector1 : inspector)
            {


                if (inspector1 !=null) {
                    elementos.add("AGREGADA" );
                }else{
                    elementos.add(" NO AGREGADA" );
                }

            }
            tabla.agregarFilaTabla(elementos);
            elementos=null;
            elementos= new ArrayList<String>();
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




        protected void onPostExecute(Void result)
        {









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



