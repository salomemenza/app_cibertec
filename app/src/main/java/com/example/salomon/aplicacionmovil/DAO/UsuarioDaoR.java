package com.example.salomon.aplicacionmovil.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.salomon.aplicacionmovil.entidad.UsuarioR;
import java.util.List;

/**
 * Created by desarrollo6 on 19/02/2018.
 */

@Dao
public interface UsuarioDaoR {
    @Insert
    void insertMultipleRecord(UsuarioR usuarios);

    @Insert
    void insertMultipleListRecord(List<UsuarioR> usuarios);

    @Insert
    long insertOnlySingleRecord(UsuarioR usuario);

    @Query("SELECT * FROM UsuarioR")
    List<UsuarioR> fetchAllData();

    @Query("SELECT * FROM UsuarioR WHERE id =:id")
    UsuarioR getSingleRecord(int id);

    @Query("SELECT * FROM UsuarioR WHERE login =:v_login")
    UsuarioR getRecordByUser(String v_login);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateRecord(UsuarioR university);

    @Delete
    void deleteRecord(UsuarioR university);
}