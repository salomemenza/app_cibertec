package com.example.salomon.aplicacionmovil.MVP;

import android.app.Application;
import android.os.Handler;
import android.text.TextUtils;

import com.example.salomon.aplicacionmovil.entidad.RecordarEntidad;
import com.example.salomon.aplicacionmovil.entidad.UsuarioR;
import com.example.salomon.aplicacionmovil.sqlite.RoomDataBase;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginInteractorImpl implements LoginInteractor{
    @Override
    public void login(final String username, final String password, final LoginInteractor.OnLoginFinishedListener listener) {
        /*new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 3000);*/

        if (TextUtils.isEmpty(password)){
            listener.onPasswordError();
            return;
        }
        if (TextUtils.isEmpty(username)){
            listener.onUsernameError();
            return;
        }

        RoomDataBase appDb = RoomDataBase.getAppDb(listener.getContext());
        UsuarioR objUsuario = appDb.getUserDao().getRecordByUser(username);

        if(objUsuario == null){
            listener.onUsernameError();
            //Toast.makeText(LoginActivity.this, "usuario no registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(objUsuario.getPassword())){
            //Toast.makeText(LoginActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            listener.onPasswordError();
            return;
        }

        listener.onSuccess();
    }

    @Override
    public void recordarUsuario(Boolean recordar, String username, final LoginInteractor.OnLoginFinishedListener listener) {
        RoomDataBase appDb = RoomDataBase.getAppDb(listener.getContext());
        RecordarEntidad recordarReg = appDb.getRecordarRoom().getRecordByUser(username);

        RecordarEntidad recordarSingle = new RecordarEntidad();
        recordarSingle.setValor(recordar);
        recordarSingle.setUsuario(username);

        if(recordarReg != null){
            //Actualizar
            appDb.getRecordarRoom().updateRecord(recordarSingle);
        }else{
            //Crear
            appDb.getRecordarRoom().insertOnlySingleRecord(recordarSingle);
        }
    }
}
