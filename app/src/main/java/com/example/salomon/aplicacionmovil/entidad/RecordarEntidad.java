package com.example.salomon.aplicacionmovil.entidad;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by desarrollo6 on 20/03/2018.
 */

@Entity()
public class RecordarEntidad {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private boolean valor;
    private String usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
