package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;

import com.example.salomon.aplicacionmovil.interactor.LoginInteractor;
import com.example.salomon.aplicacionmovil.interactor.LoginInteractorImpl;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginPresenterImpl /*implements LoginInteractor.OnLoginFinishedListener*/ {
    /*private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override public void onSuccess() {
        if (loginView != null) {
            loginView.rememberUser();
            loginView.navigateToHome();
        }
    }

    @Override
    public void onErrorLogin(String mensaje) {
        loginView.hideProgress();
        loginView.showMessage(mensaje);
    }

    @Override
    public void onRememberUser(String username) {
        loginView.showUser(username);
    }

    @Override
    public Context getContext() {
        return loginView.obtenetContexto();
    }*/
}
