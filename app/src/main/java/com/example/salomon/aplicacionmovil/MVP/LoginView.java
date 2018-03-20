package com.example.salomon.aplicacionmovil.MVP;

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

    void navigateToHome();

    Context obtenetContexto();
}
