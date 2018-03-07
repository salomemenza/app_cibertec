package com.example.salomon.aplicacionmovil;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class SolicitarMapaActivity extends AppCompatActivity {

    private Button btnCargarMapa;
    private EditText etLatitud, etLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_mapa);

        inicializarObjetos();

        btnCargarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermisoUbicacion();
            }
        });
    }

    private void solicitarPermisoUbicacion() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        //openCamera();
                        Intent i = new Intent(SolicitarMapaActivity.this, MapaReturnActivity.class);
                        startActivityForResult(i,1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void inicializarObjetos() {
        btnCargarMapa  = findViewById(R.id.btnMostrarMapa);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SolicitarMapaActivity.this);
        builder.setTitle("Se necesita permisos");
        builder.setMessage("Para que la aplicación function necesita permisos a la ubicacion. Otorge los permisos desde ajustes.");
        builder.setPositiveButton("Ir a Ajustes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("SOlicitarMapa", "requestCode: "+requestCode);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                String latitud = data.getStringExtra("Latitud");
                String longitud = data.getStringExtra("Longitud");
                Log.i(getApplication().getPackageName(), "Latitud: "+latitud);
                Log.i(getApplication().getPackageName(), "Longitud: "+longitud);
                etLatitud.setText(String.valueOf(latitud));
                etLongitud.setText(String.valueOf(longitud));
            }else{
                //Mostrar mensaje
            }
        }
    }
}
