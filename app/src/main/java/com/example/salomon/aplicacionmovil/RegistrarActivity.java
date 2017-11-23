package com.example.salomon.aplicacionmovil;

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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.salomon.aplicacionmovil.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.Utilities.StringWithTag;
import com.example.salomon.aplicacionmovil.entidad.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {
    private Spinner spnSexo;
    private Toolbar toolbar;
    private FloatingActionButton btnRegistrar;

    private EditText txtIdentificador;
    private EditText txtUsuario;
    private EditText txtContraseña;
    private EditText txtNombres;
    private EditText txtApellidos;

    private TextInputLayout lytUsuario;

    Animation animShake;

    HashMap<Integer, String> hmSexo = new HashMap<Integer, String>() {{
        put(0, "Femenino");
        put(1, "Masculino");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicarObjetos();
        poblarSpinner();

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm(view);
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

        lytUsuario = findViewById(R.id.register_lyt_usuario);

        setSupportActionBar(toolbar);
    }

    private void poblarSpinner(){
        /*String[] array_sexo = getResources().getStringArray(R.array.register_array_sexo);
        ArrayList<String> sexoList = new ArrayList<String>(Arrays.asList(array_sexo));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sexoList);
        spnSexo.setAdapter(adapter);*/

        /* Create your ArrayList collection using StringWithTag instead of String. */
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
        if (!validateText()) {
            return;
        }

        //Obtener el identificador del campo Sexo
        Integer item = spnSexo.getSelectedItemPosition();
        StringWithTag swt = (StringWithTag) spnSexo.getItemAtPosition(item);
        Integer idSexo = (Integer) swt.tag;

        Usuario usuarioEntidad = new Usuario();
        //usuarioEntidad.setCodigoUsuario(Integer.parseInt(txtIdentificador.getText().toString()));
        usuarioEntidad.setLogin(txtUsuario.getText().toString());
        usuarioEntidad.setPassword(txtContraseña.getText().toString());
        usuarioEntidad.setNombre(txtNombres.getText().toString());
        usuarioEntidad.setApellidoPaterno(txtApellidos.getText().toString());
        usuarioEntidad.setSexo(idSexo);

        UsuarioDAO usuarioModel = new UsuarioDAO(view.getContext());
        usuarioModel.insertar(usuarioEntidad);

        Snackbar.make(view, "Se registro correctamente al usuario: "+txtNombres.getText().toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private boolean validateText() {
        if (txtUsuario.getText().toString().trim().isEmpty()) {
            lytUsuario.setError("Ingrese un Usuario");
            txtUsuario.requestFocus();
            return false;
        } else {
            lytUsuario.setErrorEnabled(false);
        }
        return true;
    }

}
