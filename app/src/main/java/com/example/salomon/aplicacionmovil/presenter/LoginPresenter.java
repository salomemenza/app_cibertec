package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;
import android.util.Log;

import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioDataSource;
import com.example.salomon.aplicacionmovil.interactor.LoginInteractor;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginPresenter extends Presenter<LoginPresenter.View> implements LoginInteractor.interactorCallback {
    private LoginInteractor loginInteractor;

    public LoginPresenter (LoginInteractor interactor){
        this.loginInteractor = interactor;
    }

    public void obtenerRecuerdo() {
        loginInteractor.getUserRemember(this);
    }

    public void validateCredentials(String username, String password) {
        if (getView() != null) {
            getView().showProgress();
        }
        Log.i("LOGIN: ","Validacion de credenciales");
        loginInteractor.login(username, password, this);
    }

    public void openRegister() {
        getView().goToRegister();
    }

    public void recordarUsuario(boolean recordar, String username) {
        loginInteractor.recordarUsuario(recordar,username,this);
    }

    public Context getContext() {
        return getView().obtenetContexto();
    }

    @Override public void terminate() {
        super.terminate();
        setView(null);
    }

    @Override
    public void onUsernameError() {
        if (getView() != null) {
            getView().setUsernameError();
            getView().hideProgress();
        }
    }
    @Override
    public void onPasswordError() {
        if (getView() != null) {
            getView().setPasswordError();
            getView().hideProgress();
        }
    }

    @Override
    public void onLoginSucces() {
        if (getView() != null) {
            getView().rememberUser();
            getView().navigateToHome();
        }
    }

    @Override
    public void onLoginError(String mensaje) {
        getView().hideProgress();
        getView().showMessage(mensaje);
    }

    @Override
    public void onUserRemember(String username) {
        getView().showUser(username);
    }

    @Override
    public void onUserError(String mensaje) {
        getView().showMessage(mensaje);
    }

    @Override
    public void onUserSucces() {
        Log.i("LoginPresenter: ","Se guardo correctamente al usuario");
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
