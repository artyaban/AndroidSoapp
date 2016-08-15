package com.example.artyaban.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class nohoras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nohoras);
    }

    public void reintentar(View view)
    {
        Intent intent2 = new Intent(nohoras.this,registro.class);
        startActivity(intent2);

    }
}
