package com.example.salomon.aplicacionmovil.sqlite;

import android.database.sqlite.SQLiteException;

/**
 * Created by Salomon on 18/11/2017.
 */

public class DAOException extends SQLiteException {
    public DAOException() {
    }

    public DAOException(String error) {
        super(error);
    }

    public DAOException(String error, Throwable cause) {
        super(error, cause);
    }
}
