package com.example.salomon.aplicacionmovil.sqlite;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.salomon.aplicacionmovil.DAO.Recordar;
import com.example.salomon.aplicacionmovil.DAO.UsuarioDaoR;
import com.example.salomon.aplicacionmovil.entidad.RecordarEntidad;
import com.example.salomon.aplicacionmovil.entidad.UsuarioR;

/**
 * Created by desarrollo6 on 19/02/2018.
 */

@Database(entities = {UsuarioR.class, RecordarEntidad.class}, version = 2)
public abstract class RoomDataBase extends RoomDatabase {
    private static final String DB_NAME = "app_db";
    private static RoomDataBase appDb;

    public static RoomDataBase getAppDb(final Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context, RoomDataBase.class, DB_NAME)
                    .fallbackToDestructiveMigration() //temporary
                    .allowMainThreadQueries() //temporary
                    .build();
        }
        return appDb;
    }

    public abstract UsuarioDaoR getUserDao();

    public abstract Recordar getRecordarRoom();
}
