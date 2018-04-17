package com.example.salomon.aplicacionmovil.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.view.helper.mySession;

import butterknife.BindView;
import butterknife.OnClick;

public class PermisosActivity extends BaseActivity {
    @BindView(R.id.btnPermisos) Button btnPermiso;
    private mySession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        session = new mySession(this);
    }

    @OnClick(R.id.btnPermisos)
    public void onbtnPermisosClick(View view) {
        session.brindarPermisos();
    }
}
