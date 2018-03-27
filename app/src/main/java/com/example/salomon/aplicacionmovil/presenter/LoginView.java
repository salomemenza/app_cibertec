package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void goToRegister();

    void rememberUser();

    void navigateToHome();

    void showMessage(String mensaje);

    void showUser(String username);

    Context obtenetContexto();
}
