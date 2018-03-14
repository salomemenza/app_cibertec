package com.example.salomon.aplicacionmovil;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
import com.example.salomon.aplicacionmovil.MVP.LoginPresenter;
import com.example.salomon.aplicacionmovil.MVP.LoginPresenterImpl;
import com.example.salomon.aplicacionmovil.MVP.LoginView;
import com.example.salomon.aplicacionmovil.entidad.Usuario;
import com.example.salomon.aplicacionmovil.entidad.UsuarioR;
import com.example.salomon.aplicacionmovil.sqlite.RoomDataBase;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = "Login";
    Button btnIniciar;
    TextView txtRegistrar;
    EditText txtUsuario, txtPassword;
    private LoginPresenter presenter;
    //Mensajes
    private ProgressDialog mensajeBuilder;

    private TextInputLayout lytUsuario, lytPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constructObject();

        presenter = new LoginPresenterImpl(this);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (!validateForm()) {
                    return;
                }

                RoomDataBase appDb = RoomDataBase.getAppDb(getApplicationContext());
                UsuarioR objUsuario = appDb.getUserDao().getRecordByUser(txtUsuario.getText().toString());

                //UsuarioDAO usuarioDAO=new UsuarioDAO(view.getContext());
                //Usuario objUsuario = usuarioDAO.obtenerByUser(txtUsuario.getText().toString());

                if(objUsuario == null){
                    Toast.makeText(LoginActivity.this, "usuario no registrado", Toast.LENGTH_SHORT).show();
                    return;
                }

                String pass = txtPassword.getText().toString();
                if (TextUtils.isEmpty(objUsuario.getPassword()) || !pass.equals(objUsuario.getPassword().toString())){
                    Toast.makeText(LoginActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO Auto-generated method stub
                //Intent i = new Intent(getApplicationContext(),MenuDesignActivity.class);
                Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
                startActivity(i);*/

                presenter.validateCredentials(txtUsuario.getText().toString(), txtPassword.getText().toString());
            }
        });

        txtRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.openRegister();
            }
        });
    }

    /*RoomDataBase appDb = RoomDataBase.getAppDb(getApplicationContext());
    UsuarioR usuario = new UsuarioR();
    usuario.setLogin("admin");
    usuario.setPassword("123456");
    usuario.setNombre("admin");
    usuario.setApellidoPaterno("administrador");
    usuario.setSexo(1);

    long userId = appDb.getUserDao().insertOnlySingleRecord(usuario);
    Toast.makeText(LoginActivity.this, "Se registro correctamente al usuario: "+userId , Toast.LENGTH_SHORT).show();*/

    private void constructObject(){
        btnIniciar = findViewById(R.id.login_btn_inicio);
        txtRegistrar = findViewById(R.id.login_lbl_registrar);

        txtUsuario = findViewById(R.id.login_txt_usuario);
        txtPassword = findViewById(R.id.login_txt_password);

        lytUsuario = findViewById(R.id.login_lyt_usuario);
        lytPassword = findViewById(R.id.login_lyt_password);

        mensajeBuilder = new ProgressDialog(this);
    }

    @Override
    public void showProgress() {
        mensajeBuilder.setTitle("Validando Información");
        mensajeBuilder.setMessage("Por favor espere");
        mensajeBuilder.setCancelable(false);
        mensajeBuilder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mensajeBuilder.show();
    }

    @Override
    public void hideProgress() {
        mensajeBuilder.dismiss();
    }

    @Override
    public void setUsernameError() {
        lytUsuario.setError("Ingrese un Usuario");
        txtUsuario.requestFocus();
    }

    @Override
    public void setPasswordError() {
        lytPassword.setError("Ingrese una contraseña");
        txtPassword.requestFocus();
    }

    @Override
    public void goToRegister() {
        Intent iRegistrar = new Intent(getApplicationContext(),RegistrarActivity.class);
        startActivity(iRegistrar);
    }

    @Override
    public void navigateToHome() {
        //Intent i = new Intent(getApplicationContext(),MenuDesignActivity.class);
        Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
        startActivity(i);
        finish();
    }
}
