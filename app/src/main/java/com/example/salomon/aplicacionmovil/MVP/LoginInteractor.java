package com.example.salomon.aplicacionmovil.MVP;

import android.content.Context;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onErrorLogin(String mensaje);

        void onRememberUser(String username);

        Context getContext();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

    void recordarUsuario(Boolean recordar, String username, OnLoginFinishedListener listener);

    void getUserRemenber(OnLoginFinishedListener listener);
}
