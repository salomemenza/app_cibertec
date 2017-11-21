package com.example.salomon.aplicacionmovil.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Salomon on 18/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "aplicacionMovil.db";
    private static final int VERSION_ACTUAL = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
