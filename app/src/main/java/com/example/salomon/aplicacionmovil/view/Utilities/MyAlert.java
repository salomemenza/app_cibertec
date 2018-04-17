package com.example.salomon.aplicacionmovil.view.Utilities;

import android.app.Activity;
import android.graphics.Color;

import com.example.salomon.aplicacionmovil.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class MyAlert {
    private Activity activity;

    public MyAlert(Activity activity){
        this.activity = activity;
    }

    public void showErrorMessage(String mensaje){
        new FancyAlertDialog.Builder(activity)
                .setTitle("Ocurrio una excepcion: ")
                .setBackgroundColor(Color.parseColor("#d9534f"))
                .setMessage(mensaje)
                .setNegativeBtnText("Cancelar")
                .setPositiveBtnBackground(Color.parseColor("#d9534f"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ok")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.SLIDE)
                .isCancellable(false)
                .setIcon(R.drawable.icon_cancel, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                }).build();
    }
}
