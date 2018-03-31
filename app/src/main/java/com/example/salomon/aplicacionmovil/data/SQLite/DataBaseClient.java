package com.example.salomon.aplicacionmovil.data.SQLite;

import android.content.Context;
import android.util.Log;

import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.room.RoomDataBase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataBaseClient implements DataBaseService {
    private Context context;
    public DataBaseClient(Context context){
        this.context = context;
    }

    @Override
    public Observable<List<UsuarioR>> login(String username, String password) {
        Log.i("DATABASE_CLIENT: ","DataBase Observable");
        /*RoomDataBase appDb = RoomDataBase.getAppDb(context);
        return appDb.getUserDao().getRecordByUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
        return null;
    }
}
