package com.example.salomon.aplicacionmovil.interactor;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginInteractorImpl /*implements LoginInteractor*/ {
    /*@Override
    public void login(final String username, final String password, final LoginInteractor.OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
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
        }, 3000);

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
            listener.onErrorLogin("Usuario no se encuentra registrado");
            return;
        }

        if (!password.equals(objUsuario.getPassword())){
            listener.onErrorLogin("La contraseña es incorrecta");
            return;
        }

        listener.onSuccess();
    }

    @Override
    public void recordarUsuario(Boolean recordar, String username, final LoginInteractor.OnLoginFinishedListener listener) {
        Log.i("RecordarUsuario: ","Se recordara al usuario");
        RoomDataBase appDb = RoomDataBase.getAppDb(listener.getContext());
        RecordarEntidad recordarReg = appDb.getRecordarRoom().getRecordByUser(username);

        RecordarEntidad recordarSingle = new RecordarEntidad();
        recordarSingle.setValor(recordar);
        recordarSingle.setUsuario(username);

        appDb.getRecordarRoom().resetRecordar();
        if(recordarReg != null){
            //Actualizar
            appDb.getRecordarRoom().updateRecord(username,recordar);
        }else{
            //Crear
            appDb.getRecordarRoom().insertOnlySingleRecord(recordarSingle);
        }
    }

    @Override
    public void getUserRemenber(final LoginInteractor.OnLoginFinishedListener listener) {
        RoomDataBase appDb = RoomDataBase.getAppDb(listener.getContext());
        RecordarEntidad recordarReg = appDb.getRecordarRoom().getUserRemember();
        if(recordarReg != null){
            //Actualizar
            listener.onRememberUser(recordarReg.getUsuario());
        }
    }*/
}
