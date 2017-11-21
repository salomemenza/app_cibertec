package com.example.salomon.aplicacionmovil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.salomon.aplicacionmovil.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.entidad.Usuario;

import java.util.ArrayList;
import java.util.Arrays;

public class RegistrarActivity extends AppCompatActivity {
    private Spinner spnSexo;
    private Toolbar toolbar;
    private FloatingActionButton btnRegistrar;

    private EditText txtIdentificador;
    private EditText txtUsuario;
    private EditText txtContraseña;
    private EditText txtNombres;
    private EditText txtApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicarObjetos();
        poblarSpinner();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuarioEntidad = new Usuario();
                usuarioEntidad.setCodigoUsuario(Integer.parseInt(txtIdentificador.getText().toString()));
                usuarioEntidad.setLogin(txtUsuario.getText().toString());
                usuarioEntidad.setPassword(txtContraseña.getText().toString());
                usuarioEntidad.setNombre(txtNombres.getText().toString());
                usuarioEntidad.setApellidoPaterno(txtApellidos.toString());

                UsuarioDAO usuarioModel = new UsuarioDAO(view.getContext());
                usuarioModel.insertar(usuarioEntidad);

                Snackbar.make(view, "Se registro correctamente al usuario", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicarObjetos(){
        spnSexo = findViewById(R.id.register_spnSexo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnRegistrar = (FloatingActionButton) findViewById(R.id.register_btn_registrar);

        txtIdentificador = findViewById(R.id.register_txtIdentificador);
        txtUsuario = findViewById(R.id.register_txtUsuario);
        txtContraseña = findViewById(R.id.register_txtContraseña);
        txtNombres = findViewById(R.id.register_txtNombres);
        txtApellidos = findViewById(R.id.register_txtApellidos);

        setSupportActionBar(toolbar);
    }

    private void poblarSpinner(){
        String[] array_sexo = getResources().getStringArray(R.array.register_array_sexo);
        ArrayList<String> sexoList = new ArrayList<String>(Arrays.asList(array_sexo));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sexoList);
        spnSexo.setAdapter(adapter);
    }

}
