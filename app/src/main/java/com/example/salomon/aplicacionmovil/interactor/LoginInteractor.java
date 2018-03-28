package com.example.salomon.aplicacionmovil.interactor;

import android.content.Context;
import android.util.Log;

import com.example.salomon.aplicacionmovil.data.SQLite.DataBaseClient;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginInteractor {

    private DataBaseClient dataBaseClient;

    public LoginInteractor(DataBaseClient dataBaseClient){
        this.dataBaseClient = dataBaseClient;
    }

    public Observable<List<UsuarioR>> login(String username, String password){
        Log.i("INTERACTOR: ","Login Observable");
        return dataBaseClient.login(username,password);
    };

    /*public login(String username, String password, OnLoginFinishedListener listener){

    };

    void recordarUsuario(Boolean recordar, String username, OnLoginFinishedListener listener){

    };

    void getUserRemenber(OnLoginFinishedListener listener){

    };*/

    /*interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onErrorLogin(String mensaje);

        void onRememberUser(String username);

        Context getContext();
    }*/
}
