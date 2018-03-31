package com.example.salomon.aplicacionmovil.interactor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.salomon.aplicacionmovil.Injection;
import com.example.salomon.aplicacionmovil.data.SQLite.DataBaseClient;
import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;
import com.example.salomon.aplicacionmovil.data.model.Usuario;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioDataSource;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioRepository;
import com.example.salomon.aplicacionmovil.data.room.Recordar;
import com.example.salomon.aplicacionmovil.presenter.LoginPresenter;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginInteractor {
    private final String TAG ="LoginInteractor: ";
    private final UsuarioRepository mUsuarioRepository;

    public LoginInteractor(Context context){
        mUsuarioRepository = Injection.provideUsuarioRepository(context);
    }

    public void login(String username, final String password, final interactorCallback callback){
        Log.i(TAG,"login");

        if (TextUtils.isEmpty(password)){
            callback.onPasswordError();
            return;
        }
        if (TextUtils.isEmpty(username)){
            callback.onUsernameError();
            return;
        }

        mUsuarioRepository.getUsuario(username, new UsuarioDataSource.GetUsuarioCallback() {
            @Override
            public void onUsuariosLoaded(UsuarioR usuario) {
                if (!password.equals(usuario.getPassword())){
                    callback.onLoginError("La contraseña es incorrecta!!");
                    return;
                }
                callback.onLoginSucces();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onLoginError("Usuario no registrado!!");
            }
        });
    }

    public void getUserRemember(final interactorCallback callback){
        mUsuarioRepository.getUserRemember(new UsuarioDataSource.GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(RecordarEntidad recordar) {
                callback.onUserRemember(recordar.getUsuario());
            }

            @Override
            public void onDataNotAvailable() {
                callback.onUserError("No Existe Usuario a Recordar!!");
            }

            @Override
            public void onSuccessProccess() {
            }
        });
    };

    public void recordarUsuario(final Boolean boolRecordar, final String username, final interactorCallback callback){
        mUsuarioRepository.getUsuarioRecordarByName(username, new UsuarioDataSource.GetRecordarCallback() {
            @Override
            public void onRecordarLoaded(final RecordarEntidad recordar) {
                mUsuarioRepository.resetRemember(new UsuarioDataSource.GetRecordarCallback() {
                    @Override
                    public void onRecordarLoaded(RecordarEntidad recordar) {
                    }

                    @Override
                    public void onDataNotAvailable() {
                    }

                    @Override
                    public void onSuccessProccess() {
                        mUsuarioRepository.updateUserForRemember(boolRecordar, username, new UsuarioDataSource.GetRecordarCallback() {
                            @Override
                            public void onRecordarLoaded(RecordarEntidad recordar) {
                            }

                            @Override
                            public void onDataNotAvailable() {
                            }

                            @Override
                            public void onSuccessProccess() {
                                callback.onUserSucces();
                            }
                        });
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                RecordarEntidad mRecordar = new RecordarEntidad();
                mRecordar.setUsuario(username);
                mRecordar.setValor(boolRecordar);
                mUsuarioRepository.insertUserForRemember(mRecordar, new UsuarioDataSource.GetRecordarCallback() {
                    @Override
                    public void onRecordarLoaded(RecordarEntidad recordar) {
                    }

                    @Override
                    public void onDataNotAvailable() {
                    }

                    @Override
                    public void onSuccessProccess() {
                        callback.onUserSucces();
                    }
                });
            }

            @Override
            public void onSuccessProccess() {
            }
        });
    }

    public interface interactorCallback{
        void onUsernameError();
        void onPasswordError();
        void onLoginSucces();
        void onLoginError(String mensaje);

        void onUserRemember(String username);
        void onUserError(String mensaje);
        void onUserSucces();
    }
}
