package com.example.salomon.aplicacionmovil.MVP;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
