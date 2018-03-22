package com.example.salomon.aplicacionmovil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    CheckBox chkRecordar;
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
                presenter.validateCredentials(txtUsuario.getText().toString(), txtPassword.getText().toString());
            }
        });

        txtRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.openRegister();
            }
        });

        presenter.obtenerRecuerdo();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void constructObject(){
        btnIniciar = findViewById(R.id.login_btn_inicio);
        txtRegistrar = findViewById(R.id.login_lbl_registrar);

        txtUsuario = findViewById(R.id.login_txt_usuario);
        txtPassword = findViewById(R.id.login_txt_password);

        lytUsuario = findViewById(R.id.login_lyt_usuario);
        lytPassword = findViewById(R.id.login_lyt_password);

        chkRecordar = findViewById(R.id.chkRecordar);

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
    public void rememberUser() {
        presenter.recordarUsuario(chkRecordar.isChecked(),txtUsuario.getText().toString());
    }

    @Override
    public void navigateToHome() {
        //Intent i = new Intent(getApplicationContext(),MenuDesignActivity.class);
        Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showMessage(String mensaje) {
        Log.i("ShowMessage: ",mensaje);
        /*View main = findViewById(R.id.login_lyt_main);
        Snackbar.make(main,mensaje,Snackbar.LENGTH_SHORT);*/
        Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUser(String username) {
        Log.i("se seteara el usuario: ",username);
        txtUsuario.setText(username);
    }

    @Override
    public Context obtenetContexto() {
        return getApplicationContext();
    }
}
