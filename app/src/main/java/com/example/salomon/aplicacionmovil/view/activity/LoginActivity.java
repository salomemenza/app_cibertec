package com.example.salomon.aplicacionmovil.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.MVP.LoginPresenter;
import com.example.salomon.aplicacionmovil.MVP.LoginPresenterImpl;
import com.example.salomon.aplicacionmovil.MVP.LoginView;
import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.RegistrarActivity;
import com.example.salomon.aplicacionmovil.UsuariosActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
    private static final String TAG = "Login";

    @BindView(R.id.login_btn_inicio) Button btnIniciar;
    @BindView(R.id.login_lbl_registrar) TextView txtRegistrar;
    @BindView(R.id.login_txt_usuario) TextView txtUsuario;
    @BindView(R.id.login_txt_password) TextView txtPassword;
    @BindView(R.id.login_lyt_usuario) TextInputLayout lytUsuario;
    @BindView(R.id.login_lyt_password) TextInputLayout lytPassword;
    @BindView(R.id.chkRecordar) CheckBox chkRecordar;

    private LoginPresenter presenter;
    private ProgressDialog mensajeBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constructObject();

        presenter = new LoginPresenterImpl(this);
        presenter.obtenerRecuerdo();
    }

    @OnClick(R.id.login_btn_inicio)
    public void onLoginClick(View view) {
        presenter.validateCredentials(txtUsuario.getText().toString(), txtPassword.getText().toString());
    }

    @OnClick(R.id.login_lbl_registrar)
    public void onRegistrarClick(View view) {
        presenter.openRegister();
    }

    private void constructObject(){
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
