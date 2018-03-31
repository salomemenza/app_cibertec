package com.example.salomon.aplicacionmovil;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.salomon.aplicacionmovil.data.repository.UsuarioLocalDataSource;
import com.example.salomon.aplicacionmovil.data.repository.UsuarioRepository;
import com.example.salomon.aplicacionmovil.data.room.RoomDataBase;
import com.example.salomon.aplicacionmovil.util.AppExecutors;

public class Injection {
    public static UsuarioRepository provideUsuarioRepository(@NonNull Context context) {
        RoomDataBase database = RoomDataBase.getAppDb(context);
        return UsuarioRepository.getInstance(UsuarioLocalDataSource.getInstance(new AppExecutors(),database.getUserDao(),database.getRecordarRoom()));
    }
}
