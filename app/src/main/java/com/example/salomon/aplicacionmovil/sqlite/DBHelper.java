package com.example.salomon.aplicacionmovil.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Salomon on 18/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "DB_movil.db";
    private static final int VERSION_ACTUAL = 2;

    private static final String TAG = "SQLiteHelper";

    public DBHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Se creo la Tabla");
        db.execSQL(DataSource.CREATE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DataSource.DELETE_USUARIO);
        onCreate(db);
    }
}
