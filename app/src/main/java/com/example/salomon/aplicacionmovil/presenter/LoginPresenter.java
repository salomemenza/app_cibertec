package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;

import com.example.salomon.aplicacionmovil.MVP.LoginInteractor;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginPresenter extends Presenter<LoginPresenter.View> {
    private LoginInteractor loginInteractor;

    public LoginPresenter (LoginInteractor interactor){
        this.loginInteractor = interactor;
    }

    /*public validateCredentials(String username, String password);
    void openRegister();
    void recordarUsuario(boolean recordar, String username);
    void obtenerRecuerdo();*/

    public void validateCredentials(String username, String password) {
        if (getView() != null) {
            getView().showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    public void openRegister() {
        getView().goToRegister();
    }

    public void recordarUsuario(boolean recordar, String username) {
        loginInteractor.recordarUsuario(recordar,username,this);
    }

    public void obtenerRecuerdo() {
        loginInteractor.getUserRemenber(this);
    }

    @Override public void terminate() {
        super.terminate();
        setView(null);
    }
    public void onUsernameError() {
        if (getView() != null) {
            getView().setUsernameError();
            getView().hideProgress();
        }
    }

    public void onPasswordError() {
        if (getView() != null) {
            getView().setPasswordError();
            getView().hideProgress();
        }
    }

    public void onSuccess() {
        if (getView() != null) {
            getView().rememberUser();
            getView().navigateToHome();
        }
    }

    public void onErrorLogin(String mensaje) {
        getView().hideProgress();
        getView().showMessage(mensaje);
    }

    public void onRememberUser(String username) {
        getView().showUser(username);
    }

    public Context getContext() {
        return getView().obtenetContexto();
    }

    public interface View extends Presenter.view{
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
}
