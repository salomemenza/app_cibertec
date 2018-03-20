package com.example.salomon.aplicacionmovil.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.salomon.aplicacionmovil.entidad.RecordarEntidad;
import com.example.salomon.aplicacionmovil.entidad.UsuarioR;

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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecord(RecordarEntidad recordar);

    @Delete
    void deleteRecord(RecordarEntidad recordar);
}
