package com.example.salomon.aplicacionmovil.sqlite;

/**
 * Created by Salomon on 18/11/2017.
 */

public class DataSource {
    // DECLARA LOS NOMBRES DE LAS TABLAS
    public static final String TB_USUARIO = "usuarios";

    //TIPOS DE DATOS
    public static final String TP_STRING = "text";
    public static final String TP_INT = "integer";
    public static final String TP_NUMERIC = "real";

    //CREACIÓN DE LA TABLA

    public static final String CREATE_USUARIO =
        "create table IF NOT EXISTS "+ TB_USUARIO +"(" +
                Entidad.Usuario.codigousuario                + " " + TP_INT              + " primary key," +
                Entidad.Usuario.login                        + " " + TP_STRING           + " not null," +
                Entidad.Usuario.password                     + " " + TP_STRING           + " not null," +
                Entidad.Usuario.apellidopaterno              + " " + TP_STRING           + " not null," +
                Entidad.Usuario.nombre                       + " " + TP_STRING           + " not null) ";
}
