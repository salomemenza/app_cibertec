package com.example.salomon.aplicacionmovil.data.SQLite;

import android.content.Context;

import com.example.salomon.aplicacionmovil.data.model.UsuarioR;

import java.util.List;

import io.reactivex.Observable;

public interface DataBaseService {

    Observable<List<UsuarioR>> login(String username, String password);

    //Observable<List<Track>> getTracks(String artistId);
}
