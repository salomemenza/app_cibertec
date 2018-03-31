package com.example.salomon.aplicacionmovil.presenter;

import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.interactor.RegistrarInteractor;

public class RegistrarPresenter extends Presenter<RegistrarPresenter.View> implements RegistrarInteractor.InteractorCallback{
    private RegistrarInteractor registrarInteractor;

    public RegistrarPresenter(RegistrarInteractor interactor){
        this.registrarInteractor = interactor;
    }

    public void registrarUsuario(UsuarioR objUsuario){
        registrarInteractor.registerUser(objUsuario,this);
    }

    @Override
    public void onSuccessRegister(UsuarioR usuario) {
        getView().resetearFomulario();
        getView().showMessage("Se registro al usuario: "+usuario.getLogin());
    }

    @Override
    public void onError(String mensaje) {
        getView().showMessage(mensaje);
    }

    public interface View extends Presenter.view{
        void resetearFomulario();
        void showMessage(String mensaje);
    }
}
