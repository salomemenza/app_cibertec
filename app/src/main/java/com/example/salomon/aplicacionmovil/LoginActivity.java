package com.example.salomon.aplicacionmovil;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.entidad.Usuario;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MD";
    Button btnIniciar;
    TextView txtRegistrar;
    EditText txtUsuario, txtPassword;

    private TextInputLayout lytUsuario, lytPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constructObject();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateForm()) {
                    return;
                }

                UsuarioDAO usuarioDAO=new UsuarioDAO(view.getContext());
                Usuario objUsuario = usuarioDAO.obtenerByUser(txtUsuario.getText().toString());

                Log.i(TAG, "ob Password : "+objUsuario.getPassword() );
                Log.i(TAG, "txt Pasword: "+txtPassword.getText().toString());

                String pass = txtPassword.getText().toString();
                if (TextUtils.isEmpty(objUsuario.getPassword()) || !pass.equals(objUsuario.getPassword().toString())){
                    Toast.makeText(LoginActivity.this, "Contraseña Incorrecta o usuario no registrado", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO Auto-generated method stub
                //Intent i = new Intent(getApplicationContext(),MenuDesignActivity.class);
                Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
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

    private boolean validateForm() {
        if (txtUsuario.getText().toString().trim().isEmpty()) {
            lytUsuario.setError("Ingrese un Usuario");
            txtUsuario.requestFocus();
            return false;
        } else {
            lytUsuario.setErrorEnabled(false);
        }

        if (txtPassword.getText().toString().trim().isEmpty()) {
            lytPassword.setError("Ingrese una contraseña");
            txtPassword.requestFocus();
            return false;
        } else {
            lytPassword.setErrorEnabled(false);
        }

        return true;
    }

    private void constructObject(){
        btnIniciar = findViewById(R.id.login_btn_inicio);
        txtRegistrar = findViewById(R.id.login_lbl_registrar);

        txtUsuario = findViewById(R.id.login_txt_usuario);
        txtPassword = findViewById(R.id.login_txt_password);

        lytUsuario = findViewById(R.id.login_lyt_usuario);
        lytPassword = findViewById(R.id.login_lyt_password);
    }

}
