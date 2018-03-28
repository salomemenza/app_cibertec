package com.example.salomon.aplicacionmovil.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;

import java.util.List;

/**
 * Created by desarrollo6 on 20/03/2018.
 */
@Dao
public interface Recordar {

    @Insert
    void insertMultipleListRecord(List<RecordarEntidad> recordar);

    @Insert
    long insertOnlySingleRecord(RecordarEntidad recordar);

    @Query("SELECT * FROM RecordarEntidad")
    List<RecordarEntidad> fetchAllData();

    @Query("SELECT * FROM RecordarEntidad WHERE usuario =:usuario")
    RecordarEntidad getRecordByUser(String usuario);

    @Query("SELECT * FROM RecordarEntidad WHERE valor=1")
    RecordarEntidad getUserRemember();

    @Query("UPDATE RecordarEntidad SET valor =:valor WHERE usuario =:usuario")
    void updateRecord(String usuario, Boolean valor);

    @Delete
    void deleteRecord(RecordarEntidad recordar);

    @Query("UPDATE RecordarEntidad SET valor = 0")
    void resetRecordar();
}
