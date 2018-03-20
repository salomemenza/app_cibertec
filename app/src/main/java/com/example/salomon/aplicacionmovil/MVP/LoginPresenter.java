package com.example.salomon.aplicacionmovil.MVP;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void openRegister();

    void recordarUsuario(boolean recordar, String username);

    void onDestroy();
}
