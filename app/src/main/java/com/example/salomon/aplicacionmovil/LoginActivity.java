package com.example.salomon.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MD";
    Button btnIniciar;
    TextView txtRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIniciar = findViewById(R.id.login_btn_inicio);
        txtRegistrar = findViewById(R.id.login_lbl_registrar);

        /*btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });*/

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i(TAG, "onClick:    INICAR :P");
                Intent i = new Intent(getApplicationContext(),MenuDesignActivity.class);
                Log.i(TAG, "onClick:    INICAR 222 :P");
                startActivity(i);
            }
        });

        txtRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent iRegistrar = new Intent(getApplicationContext(),RegistrarActivity.class);
                startActivity(iRegistrar);
            }
        });
    }
}
