package com.example.salomon.aplicacionmovil.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.data.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.interactor.RegistrarInteractor;
import com.example.salomon.aplicacionmovil.presenter.RegistrarPresenter;
import com.example.salomon.aplicacionmovil.view.Utilities.StringWithTag;
import com.example.salomon.aplicacionmovil.data.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrarActivity extends BaseActivity implements RegistrarPresenter.View{
    @BindView(R.id.register_spnSexo) Spinner spnSexo;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.register_btn_registrar) FloatingActionButton btnRegistrar;

    @BindView(R.id.register_txtIdentificador) EditText txtIdentificador;
    @BindView(R.id.register_txtUsuario) EditText txtUsuario;
    @BindView(R.id.register_txtContraseña) EditText txtContraseña;
    @BindView(R.id.register_txtNombres) EditText txtNombres;
    @BindView(R.id.register_txtApellidos) EditText txtApellidos;

    @BindView(R.id.register_lyt_usuario) TextInputLayout lytUsuario;
    @BindView(R.id.register_lyt_contrasenia) TextInputLayout lytContrasenia;
    @BindView(R.id.register_lyt_nombres) TextInputLayout lytNombres;
    @BindView(R.id.register_lyt_apellidos) TextInputLayout lytApellidos;

    HashMap<Integer, String> hmSexo = new HashMap<Integer, String>() {{
        put(0, "Femenino");
        put(1, "Masculino");
    }};
    private RegistrarPresenter registrarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registrarPresenter = new RegistrarPresenter(new RegistrarInteractor(getApplicationContext()));
        registrarPresenter.setView(this);

        poblarSpinner();
    }

    @OnClick(R.id.register_btn_registrar)
    public void onRegisterClick(View view) {
        submitForm(view);
    }

    private void poblarSpinner(){
        List<StringWithTag> sexoList = new ArrayList<StringWithTag>();

        /* Iterate through your original collection, in this case defined with an Integer key and String value. */
        for (Map.Entry<Integer, String> entry : hmSexo.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            /* Build the StringWithTag List using these keys and values. */
            sexoList.add(new StringWithTag(value, key));
        }

        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        ArrayAdapter<StringWithTag> spinnerAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, sexoList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSexo.setAdapter(spinnerAdapter);
    }

    public void submitForm(View view){
        if (!validateForm()) {
            return;
        }

        //Obtener el identificador del campo Sexo
        Integer item = spnSexo.getSelectedItemPosition();
        StringWithTag swt = (StringWithTag) spnSexo.getItemAtPosition(item);
        Integer idSexo = (Integer) swt.tag;

        UsuarioR objUsuario = new UsuarioR();
        objUsuario.setLogin(txtUsuario.getText().toString());
        objUsuario.setPassword(txtContraseña.getText().toString());
        objUsuario.setNombre(txtNombres.getText().toString());
        objUsuario.setApellidoPaterno(txtApellidos.getText().toString());
        objUsuario.setSexo(idSexo);

        registrarPresenter.registrarUsuario(objUsuario);
    }

    private boolean validateForm() {
        if (txtUsuario.getText().toString().trim().isEmpty()) {
            setFormError(lytUsuario,txtUsuario,"Ingrese un Usuario");
            return false;
        } else {
            setLayoutOk(lytUsuario);
        }

        if (txtContraseña.getText().toString().trim().isEmpty()) {
            setFormError(lytContrasenia,txtContraseña,"Ingrese una contraseña");
            return false;
        } else {
            setLayoutOk(lytContrasenia);
        }

        if (txtNombres.getText().toString().trim().isEmpty()) {
            setFormError(lytNombres,txtNombres,"Ingrese sus nombres");
            return false;
        } else {
            setLayoutOk(lytNombres);
        }

        if (txtApellidos.getText().toString().trim().isEmpty()) {
            setFormError(lytApellidos,txtApellidos,"Ingrese sus Apellidos");
            return false;
        } else {
            setLayoutOk(lytApellidos);
        }

        return true;
    }

    private void setFormError(TextInputLayout layout, EditText text, String mensaje){
        layout.setError(mensaje);
        text.requestFocus();
    }

    private void setLayoutOk(TextInputLayout layout){
        layout.setCounterEnabled(false);
    }

    @Override
    public void resetearFomulario() {
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
    }

    @Override
    public void showMessage(String mensaje) {
        Snackbar.make(findViewById(R.id.coordinatorMain), mensaje, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
