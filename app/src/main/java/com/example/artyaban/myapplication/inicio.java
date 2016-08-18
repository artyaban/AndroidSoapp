package com.example.artyaban.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class inicio extends AppCompatActivity {
    public static String lider;
    String TAG = "Response";
    Button bt;


    SoapPrimitive resultString;
    SoapPrimitive resultString2;
     String usuarioo  ;
    String passwordd;
    View view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        bt = (Button) findViewById(R.id.bt);

    String usuariooo;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    view2=view;
                EditText usuario = (EditText) findViewById(R.id.editText);
                EditText password = (EditText) findViewById(R.id.editText2);

                 usuarioo = usuario.getText().toString();
                 passwordd = password.getText().toString();



                lider=passwordd.substring(0,5);
                lider=passwordd.substring(0,5);
                lider=passwordd.substring(0,5);




                if(usuarioo.equals("") || passwordd.equals(""))
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(inicio.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("POR FAVOR INGRESA USUARIO Y CONTRASEÑA");
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
        });
    }

    public void olvido(View view)
    {
        Intent cambiar = new Intent(getApplicationContext(), olvidopass.class);
        startActivityForResult(cambiar, 0);
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
               lider=passwordd.substring(0,5);
               lider=passwordd.substring(0,5);
               lider=passwordd.substring(0,5);
               lider=passwordd.substring(0,5);
               lider=passwordd.substring(0,5);

               if(resultString2.toString().equals("true"))
               {
                   Intent cambiar = new Intent(getApplicationContext(), lista.class);
                   startActivityForResult(cambiar, 0);

               }else{

                   Intent cambiar = new Intent(getApplicationContext(), registro.class);
                   startActivityForResult(cambiar, 0);


               }

           }else{
               Log.i(TAG, "estoy en else");
               AlertDialog alertDialog = new AlertDialog.Builder(inicio.this).create();
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

            Request.addProperty("user", usuarioo);
            Request.addProperty("pass", passwordd);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();


            String SOAP_ACTION2 = "http://tempuri.org/checalista";
            String METHOD_NAME2 = "checalista";
            String NAMESPACE2 = "http://tempuri.org/";
            String URL2 = "http://187.188.159.205:8090/webServiceNomina/Service.asmx";

            try {
                SoapObject Request2 = new SoapObject(NAMESPACE2, METHOD_NAME2);



                lider=passwordd.substring(0,5);
                lider=passwordd.substring(0,5);
                lider=passwordd.substring(0,5);

                Request2.addProperty("lider", lider);


                SoapSerializationEnvelope soapEnvelope2 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope2.dotNet = true;
                soapEnvelope2.setOutputSoapObject(Request2);

                HttpTransportSE transport2 = new HttpTransportSE(URL2);

                transport.call(SOAP_ACTION2, soapEnvelope2);
                resultString2 = (SoapPrimitive) soapEnvelope2.getResponse();


            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }





        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }




    }
}
