package com.example.salomon.aplicacionmovil.data.repository;

import android.support.annotation.NonNull;

import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;

public class UsuarioRepository implements UsuarioDataSource {
    private static UsuarioRepository INSTANCE = null;
    private final UsuarioDataSource mUsuarioDataSource;

    public UsuarioRepository(@NonNull UsuarioDataSource usuarioDataSource){
        mUsuarioDataSource = usuarioDataSource;
    }

    public static UsuarioRepository getInstance(UsuarioDataSource usuarioDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UsuarioRepository(usuarioDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void getUsuarios(@NonNull LoadUsuariosCallback callback) {
    }

    @Override
    public void getUsuario(@NonNull String username, @NonNull final GetUsuarioCallback callback) {
        mUsuarioDataSource.getUsuario(username, new GetUsuarioCallback() {
            @Override
            public void onUsuariosLoaded(UsuarioR usuario) {
                callback.onUsuariosLoaded(usuario);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void insertUsuario(@NonNull final UsuarioR usuario, @NonNull final GetUsuarioCallback callback) {
        mUsuarioDataSource.insertUsuario(usuario, new GetUsuarioCallback() {
            @Override
            public void onUsuariosLoaded(UsuarioR usuario) {
                callback.onUsuariosLoaded(usuario);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUserRemember(@NonNull final GetRecordarCallback callback) {
        mUsuarioDataSource.getUserRemember(new GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
                callback.onRecordarLoaded(recordar);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onSuccessProccess() {

            }
        });
    }

    @Override
    public void getUsuarioRecordarByName(@NonNull String username, @NonNull final GetRecordarCallback callback) {
        mUsuarioDataSource.getUserRemember(new GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
                callback.onRecordarLoaded(recordar);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onSuccessProccess() {

            }
        });
    }

    @Override
    public void insertUserForRemember(@NonNull RecordarEntidad recordar, @NonNull final GetRecordarCallback callback) {
        mUsuarioDataSource.insertUserForRemember(recordar, new GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
                callback.onRecordarLoaded(recordar);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onSuccessProccess() {

            }
        });
    }

    @Override
    public void updateUserForRemember(@NonNull Boolean valor, @NonNull String username, @NonNull final GetRecordarCallback callback) {
        mUsuarioDataSource.updateUserForRemember(valor, username, new GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
            }

            @Override
            public void onDataNotAvailable() {
            }

            @Override
            public void onSuccessProccess() {
                callback.onSuccessProccess();
            }
        });
    }

    @Override
    public void resetRemember(@NonNull final GetRecordarCallback callback) {
        mUsuarioDataSource.resetRemember(new GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
            }

            @Override
            public void onDataNotAvailable() {
            }

            @Override
            public void onSuccessProccess() {
                callback.onSuccessProccess();
            }
        });
    }
}
