package com.example.salomon.aplicacionmovil.interactor;

import android.content.Context;

import com.example.salomon.aplicacionmovil.Injection;
import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioDataSource;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioRepository;

public class SplashInteractor {
    private final UsuarioRepository mUsuarioRepository;

    public SplashInteractor(Context context){
        mUsuarioRepository = Injection.provideUsuarioRepository(context);
    }

    public void registerUser(final UsuarioR objUsuario, final InteractorCallback callbackPresenter ){
        mUsuarioRepository.getUsuario(objUsuario.getLogin(), new UsuarioDataSource.GetUsuarioCallback() {
            @Override
            public void onUsuariosLoaded(UsuarioR usuario) {
                callbackPresenter.onError("El usuario: "+objUsuario.getNombre()+" ya se encuentra registrado");
            }

            @Override
            public void onDataNotAvailable() {
                mUsuarioRepository.insertUsuario(objUsuario, new UsuarioDataSource.GetUsuarioCallback() {
                    @Override
                    public void onUsuariosLoaded(UsuarioR usuario) {
                        callbackPresenter.onSuccessRegister(usuario);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callbackPresenter.onError("Ocurrio un error al registrar al usuario");
                    }
                });
            }
        });
    }

    public interface InteractorCallback{
        void onSuccessRegister(UsuarioR usuario);
        void onError(String mensaje);
    }
}
