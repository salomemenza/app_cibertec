package com.example.salomon.aplicacionmovil;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.entidad.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
    private ArrayList<HashMap<String,String>> listaUsuario = new ArrayList<HashMap<String, String>>();

    private final String TAG = "splash";

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /** Called when the activity is first created. */
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimations();
        createUserDefault();
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
                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, MapaReturnActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
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
            String usuario = map.get("usuario");

            UsuarioDAO usuarioModel = new UsuarioDAO(this);
            Usuario objUsuario = usuarioModel.obtenerByUser(usuario);

            if (TextUtils.isEmpty(objUsuario.getLogin())){
                Usuario usuarioEntidad = new Usuario();
                usuarioEntidad.setLogin(usuario);

                usuarioEntidad.setLogin(usuario);
                usuarioEntidad.setPassword(map.get("password"));
                usuarioEntidad.setNombre(map.get("nombre"));
                usuarioEntidad.setApellidoPaterno(map.get("paterno"));
                usuarioEntidad.setSexo(Integer.parseInt(map.get("sexo")));

                usuarioModel.insertar(usuarioEntidad);
                Toast.makeText(SplashActivity.this, "Se registro correctamente al usuario: "+ usuario, Toast.LENGTH_SHORT).show();
            }
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
}
