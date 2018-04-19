package com.example.salomon.aplicacionmovil.view.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;

import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.view.activity.PermisosActivity;
import com.example.salomon.aplicacionmovil.view.activity.SplashActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

public class mySession {
    private Activity mActivity;
    private SessionListener mSessionListener;

    public mySession(Activity activity){
        this.mActivity = activity;
    }

    public mySession(Activity activity, SessionListener sessionListener){
        this.mActivity = activity;
        this.mSessionListener = sessionListener;
    }

    public void verificarPermisos(){
        String permisoLocation = android.Manifest.permission.ACCESS_FINE_LOCATION;
        String permisoTelefono = android.Manifest.permission.CALL_PHONE;
        String permisoCamara = android.Manifest.permission.CAMERA;
        String permisoMensaje = android.Manifest.permission.READ_SMS;

        int resLocation = mActivity.getApplicationContext().checkCallingOrSelfPermission(permisoLocation);
        int resTelefono = mActivity.getApplicationContext().checkCallingOrSelfPermission(permisoTelefono);
        int resCamara = mActivity.getApplicationContext().checkCallingOrSelfPermission(permisoCamara);
        int resMensaje = mActivity.getApplicationContext().checkCallingOrSelfPermission(permisoMensaje);


        if(resLocation == PackageManager.PERMISSION_GRANTED && resTelefono == PackageManager.PERMISSION_GRANTED &&
                resCamara == PackageManager.PERMISSION_GRANTED && resMensaje == PackageManager.PERMISSION_GRANTED){
        }else{
            mSessionListener.onDenied();
        }

        /*Dexter.withActivity(mActivity)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_SMS)
                .withListener(new MultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){

                        }

                        if(report.isAnyPermissionPermanentlyDenied()){

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();*/
    }

    private void showSettingsDialog() {

        new FancyAlertDialog.Builder(mActivity)
                //.setTitle("Ocurrio una excepcion: ")
                .setBackgroundColor(Color.parseColor("#DF852C"))
                .setMessage("Para que la aplicación funcione necesita conceder permisos")
                .setNegativeBtnText("Cancelar")
                .setPositiveBtnBackground(Color.parseColor("#4CAF50"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ok")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.SLIDE)
                .isCancellable(false)
                .setIcon(R.drawable.icon_cancel, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        openSettings();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                }).build();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
        intent.setData(uri);
        mActivity.startActivityForResult(intent,101);
    }

    public void brindarPermisos(){
        Dexter.withActivity(mActivity)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_SMS)
                .withListener(new MultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            Intent intent = new Intent(mActivity.getApplicationContext(), SplashActivity.class);
                            mActivity.startActivity(intent);
                            mActivity.finish();
                        }

                        if(report.isAnyPermissionPermanentlyDenied()){
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    public interface SessionListener{
        void onDenied();
    }
}
