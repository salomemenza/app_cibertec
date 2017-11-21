package com.example.salomon.aplicacionmovil;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MenuActivity extends AppCompatActivity {
    String arrayOptions[] = { "Inicio","Buscar","Notificaciones","Configuracion","Localizar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CircleMenu menuCircular = findViewById(R.id.CircleMenu);
        menuCircular.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.icon_menu, R.drawable.icon_cancel)
            .addSubMenu(Color.parseColor("#258CFF"), R.drawable.icon_home)
            .addSubMenu(Color.parseColor("#30A400"), R.drawable.icon_search)
            .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.icon_notify)
            .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.icon_setting)
            .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.icon_gps)
            .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(MenuActivity.this, "Seleccionaste: "+arrayOptions[index], Toast.LENGTH_SHORT).show();
                    }

            }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
                @Override
                public void onMenuOpened() {}

                @Override
                public void onMenuClosed() {}
            });
    }
}
