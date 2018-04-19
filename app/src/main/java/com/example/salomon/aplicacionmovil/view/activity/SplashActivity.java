package com.example.salomon.aplicacionmovil.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.room.RoomDataBase;
import com.example.salomon.aplicacionmovil.interactor.SplashInteractor;
import com.example.salomon.aplicacionmovil.presenter.SplashPresenter;
import com.example.salomon.aplicacionmovil.view.helper.mySession;

import java.util.ArrayList;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.View, mySession.SessionListener {
    private ArrayList<HashMap<String,String>> listaUsuario = new ArrayList<HashMap<String, String>>();
    private final String TAG = "splash";
    private SplashPresenter splashPresenter;
    private ProgressBar progressBar;
    private boolean todosPermisos = true;
    private mySession session;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /** Called when the activity is first created. */
    Thread splashTread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter(new SplashInteractor(getApplicationContext()));
        splashPresenter.setView(this);

        progressBar = findViewById(R.id.prbLoad);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        session = new mySession(this,this);
        StartAnimations();
        createUserDefault();
    }

    @Override
    protected void onResume() {
        super.onResume();
        todosPermisos = true;
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l= findViewById(R.id.splashLyContent);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = findViewById(R.id.splahImgLogo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }

                    session.verificarPermisos();
                    if(todosPermisos){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }
            }
        };
        splashTread.start();
    }

    private void createUserDefault(){
        //Primer Usuario
        poblarLista("admi","123","Boss", "administrador","1");
        poblarLista("root","123","Boss", "Root","1");
        poblarLista("admin","123","Boss", "administrador","0");
        poblarLista("boss","123","Boss", "administrador","0");

        for(HashMap<String,String> map: listaUsuario){
            UsuarioR usuarioRoom = new UsuarioR();
            usuarioRoom.setLogin(map.get("usuario"));
            usuarioRoom.setPassword(map.get("password"));
            usuarioRoom.setNombre(map.get("nombre"));
            usuarioRoom.setApellidoPaterno(map.get("paterno"));
            usuarioRoom.setSexo(Integer.parseInt(map.get("sexo")));
            splashPresenter.registrarUsuario(usuarioRoom);
        }
    }

    private void poblarLista(String usuario, String password, String paterno, String nombre, String sexo){
        HashMap<String,String> hmUsuario = new HashMap<String,String>();

        hmUsuario.put("usuario",usuario);
        hmUsuario.put("password",password);
        hmUsuario.put("paterno",paterno);
        hmUsuario.put("nombre", nombre);
        hmUsuario.put("sexo",sexo);
        listaUsuario.add(hmUsuario);
    }

    @Override
    public void showMessage(String mensaje) {
        //Toast.makeText(SplashActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context obtenetContexto() {
        return getApplicationContext();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    @Override
    public void onDenied() {
        todosPermisos = false;
        Intent intent = new Intent(SplashActivity.this, PermisosActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
