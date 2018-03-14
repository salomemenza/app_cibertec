package com.example.salomon.aplicacionmovil.MVP;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by desarrollo6 on 13/03/2018.
 */

public class LoginInteractorImpl implements LoginInteractor{
    @Override
    public void login(final String username, final String password, final LoginInteractor.OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error = true;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 3000);
    }
}
