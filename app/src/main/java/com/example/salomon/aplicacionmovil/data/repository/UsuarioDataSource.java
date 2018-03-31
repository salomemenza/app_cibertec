package com.example.salomon.aplicacionmovil.data.repository;

import android.support.annotation.NonNull;

import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;

import java.util.List;

public interface UsuarioDataSource {
    interface LoadUsuariosCallback{
        void onUsuariosLoaded(List<UsuarioR> usuarios);
        void onDataNotAvailable();
    }

    interface GetUsuarioCallback{
        void onUsuariosLoaded(UsuarioR usuario);
        void onDataNotAvailable();
    }

    interface GetRecordarCallback{
        void onRecordarLoaded(RecordarEntidad recordar);
        void onDataNotAvailable();
        void onSuccessProccess();
    }

    void getUsuarios(@NonNull LoadUsuariosCallback callback);
    void getUsuario(@NonNull String username, @NonNull GetUsuarioCallback callback);

    void getUserRemember(@NonNull GetRecordarCallback callback);
    void getUsuarioRecordarByName(@NonNull String username, @NonNull GetRecordarCallback callback);
    void insertUserForRemember(@NonNull RecordarEntidad recordar, @NonNull GetRecordarCallback callback);
    void updateUserForRemember(@NonNull Boolean valor, @NonNull String username, @NonNull GetRecordarCallback callback);
    void resetRemember(@NonNull GetRecordarCallback callback);

}
