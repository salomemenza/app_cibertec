package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;

import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.interactor.SplashInteractor;

public class SplashPresenter extends Presenter<SplashPresenter.View> implements SplashInteractor.InteractorCallback{
    private SplashInteractor splashInteractor;

    public SplashPresenter(SplashInteractor interactor){
        this.splashInteractor = interactor;
    }

    public void registrarUsuario(UsuarioR objUsuario){
        splashInteractor.registerUser(objUsuario,this);
    }

    @Override
    public void onSuccessRegister(UsuarioR usuario) {
        //getView().showMessage("Se regsitró al usuario: "+ usuario.getNombre());
    }

    @Override
    public void onError(String mensaje) {
        getView().showMessage(mensaje);
    }

    public interface View extends Presenter.view{
        void showMessage(String mensaje);
        Context obtenetContexto();
    }
}
