package com.example.salomon.aplicacionmovil.MVP;

import android.content.Context;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener{
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void openRegister() {
        loginView.goToRegister();
    }

    @Override
    public void recordarUsuario(boolean recordar, String username) {
    
    }

    @Override public void onDestroy() {
        loginView = null;
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
    public Context getContext() {
        return loginView.obtenetContexto();
    }
}
